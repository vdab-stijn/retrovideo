package be.vdab.retrovideo.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.entities.Movie;
import be.vdab.retrovideo.services.CustomerService;
import be.vdab.retrovideo.services.GenreService;
import be.vdab.retrovideo.services.MovieService;
import be.vdab.retrovideo.services.ReservationService;
import be.vdab.retrovideo.web.basket.MovieBasket;
import be.vdab.retrovideo.web.basket.MovieBasketForm;
import be.vdab.retrovideo.web.forms.ReservationConfirmationForm;

@Controller
@RequestMapping("/basket")
public class BasketController {
	
	private static final Logger LOGGER
	= LoggerFactory.getLogger(BasketController.class);	

	private final GenreService genreService;
	private final MovieService movieService;
	private final CustomerService customerService;
	private final ReservationService reservationService;
	private final MovieBasket basket;
	
	public BasketController(
			final GenreService genreService,
			final MovieService movieService,
			final CustomerService customerService,
			final ReservationService reservationService,
			final MovieBasket basket) {
		this.genreService = genreService;
		this.movieService = movieService;
		this.customerService = customerService;
		this.reservationService = reservationService;
		this.basket = basket;
	}
	
	private final List<Movie> unmarshallMovies(final List<Long> movieIds) {
		if (movieIds == null || movieIds.size() == 0)
			return new ArrayList<Movie>();
		
		List<Movie> movies = new ArrayList<>(movieIds.size());
		
		for (long movieId : movieIds)
			movieService.read(movieId).ifPresent(movie -> movies.add(movie));
		
		return movies;
	}
	
//	private final Customer unmarshallCustomer(final long customerId) {
//		return customerService.read(customerId).get();
//	}
	
	private static final String VIEW_BASKET = "basket";
	@GetMapping
	public final ModelAndView showBasket() {
		final ModelAndView modelAndView = new ModelAndView(VIEW_BASKET)
				.addObject(new MovieBasketForm())
				.addObject("genres", genreService.findAll())
				.addObject("total",
						movieService.countTotal(basket.getMovieIds()))
				.addObject("movies", unmarshallMovies(
						basket.getMovieIds()));
		
		if (basket.hasCustomerId())
			customerService.read(basket.getCustomerId()).ifPresent(customer ->
				modelAndView.addObject("customer", customer));
		
		return modelAndView;
	}
	
	private static final String REDIRECT_AFTER_ADD = "redirect:/basket";
	@GetMapping("{id}")
	public final String addMovieToBasket(@PathVariable final long id) {
		final Optional<Movie> movie = movieService.read(id);
		
		// BASKET SAFETY
		if (movie.isPresent() && movie.get().canBeReserved())
			basket.addMovieId(id);
		
		return REDIRECT_AFTER_ADD;
	}
	
	private static final String REDIRECT_AFTER_DELETE = "redirect:/basket";
	@PostMapping
	public final String removeMovie(
			@Valid final MovieBasketForm form,
			final BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return REDIRECT_AFTER_DELETE;
		
		for (final long movieId : form.getMovieId())
			basket.deleteMovieId(movieId);
		
		return REDIRECT_AFTER_DELETE;
	}
	
	private static final String VIEW_RESERVATION = "reservation";
	@GetMapping("/reservation/{id}")
	public final ModelAndView showReservation(@PathVariable final long id) {
		final ModelAndView modelAndView = new ModelAndView(VIEW_RESERVATION);
		
		customerService.read(id).ifPresent(customer -> {
				modelAndView.addObject("customer", customer);
				basket.setCustomerId(customer.getId());
		});
		
		return modelAndView
				.addObject(new ReservationConfirmationForm())
				.addObject("genres", genreService.findAll())
				.addObject("total",
						movieService.countTotal(basket.getMovieIds()))
				.addObject("movies", unmarshallMovies(basket.getMovieIds()));
	}
	
	private static final String REDIRECT_AFTER_CONFIRMATION = "redirect:/basket/status";
	@PostMapping(path = "/reservation/{id}")
	public final String makeReservation(
			@PathVariable final long id,
			@Valid final ReservationConfirmationForm form,
			final BindingResult bindingResult) {
		final List<Long> intermittentlyReserved = new ArrayList<>();
		final List<Long> alreadyReserved = new ArrayList<>();
		final List<Long> failedStockUpdate;
		final List<Long> failedReservation;
		
		// What if a movie can no longer be reserved by the time
		// the employee confirms the reservation ?
		final List<Movie> movies = unmarshallMovies(basket.getMovieIds());
		for (final Movie movie : movies)
			if (!movie.canBeReserved())
				intermittentlyReserved.add(movie.getId());
		for (final long movieId : intermittentlyReserved)
			basket.deleteMovieId(movieId);
		
		// What if the customer already reserved the same movie previously ?
		for (final long movieId : basket.getMovieIds())
			if (reservationService.hasReserved(
					basket.getCustomerId(), movieId))
				alreadyReserved.add(movieId);
		for (final long movieId : alreadyReserved)
			basket.deleteMovieId(movieId);
		
		
		// JDBC failures: errors executing the update on the movies table
		failedStockUpdate = movieService.reserveMovies(basket.getMovieIds());
		if (failedStockUpdate.size() > 0) {
			LOGGER.error("Failed to update reservations for movies (JDBC): " +
					failedStockUpdate.size() + " movie(s)");
			
			// Remove failed updates, keep successful ones
			for (final long movieId : failedStockUpdate)
				basket.deleteMovieId(movieId);
		}
		
		// JDBC failures: errors creating a record in the reservations table
		if (failedStockUpdate.size() > 0)
			// If there were already errors, go no further
			failedReservation = new ArrayList<>();
		else {
			failedReservation = reservationService.reserveMovies(
					basket.getCustomerId(),
					basket.getMovieIds());
			
			if (failedReservation.size() > 0) {
				LOGGER.error("Failed to create reservations (JDBC): " +
						failedReservation.size() + " movie(s)");
			}
			
			// Remove failed updates, keep successful ones
			for (final long movieId : failedReservation)
				basket.deleteMovieId(movieId);
		}

		// basket movieIds should now only contain successfully reserved ids
		basket.setIntermittentlyReservedMovieIds(intermittentlyReserved);
		basket.setAlreadyReservedMovieIds(alreadyReserved);
		basket.setStockUpdateFailureMovieIds(failedStockUpdate);
		basket.setReservationCreationFailureMovieIds(failedReservation);
		
		return REDIRECT_AFTER_CONFIRMATION;
	}
	
	private static final String VIEW_DISPLAY_RESULT = "status";
	@GetMapping("/status")
	public final ModelAndView showReservationStatus() {
		final boolean success = basket.success();
		
		if (success) {
			LOGGER.info("MOVIE RESERVATION: success");
		}
		
		final ModelAndView modelAndView = new ModelAndView(VIEW_DISPLAY_RESULT)
				.addObject("genres", genreService.findAll())
				.addObject("success", success)
				.addObject("successfullyReserved",
						unmarshallMovies(
							basket.getSuccessfullyReservedMovieIds()))
				.addObject("failIntermittentlyReserved",
						unmarshallMovies(
							basket.getIntermittentlyReservedMovieIds()))
				.addObject("failAlreadyReserved",
						unmarshallMovies(
								basket.getAlreadyReservedMovieIds()))
				.addObject("failStockUpdate",
						unmarshallMovies(
							basket.getStockUpdateFailureMovieIds()))
				.addObject("failReservation",
						unmarshallMovies(
							basket.getReservationCreationFailureMovieIds()));
		
		basket.clearBasket();
		
		return modelAndView;
	}
}
