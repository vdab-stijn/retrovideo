package be.vdab.retrovideo.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import be.vdab.retrovideo.web.basket.MovieBasket;
import be.vdab.retrovideo.web.basket.MovieBasketForm;
import be.vdab.retrovideo.web.forms.ReservationConfirmationForm;

@Controller
@RequestMapping("/basket")
public class BasketController {

	private final GenreService genreService;
	private final MovieService movieService;
	private final CustomerService customerService;
	private final MovieBasket basket;
	
	public BasketController(
			final GenreService genreService,
			final MovieService movieService,
			final CustomerService customerService,
			final MovieBasket basket) {
		this.genreService = genreService;
		this.movieService = movieService;
		this.customerService = customerService;
		this.basket = basket;
	}
	
	private final List<Movie> unmarshallBasket(final List<Long> movieIds) {
		if (movieIds == null || movieIds.size() == 0)
			return new ArrayList<Movie>();
		
		List<Movie> movies = new ArrayList<>(movieIds.size());
		
		for (long movieId : movieIds)
			movieService.read(movieId).ifPresent(movie -> movies.add(movie));
		
		return movies;
	}
	
	private static final String VIEW_BASKET = "basket";
	@GetMapping
	public final ModelAndView showBasket() {
		return new ModelAndView(VIEW_BASKET)
				.addObject(new MovieBasketForm())
				.addObject("genres", genreService.findAll())
				.addObject("total",
						movieService.countTotal(basket.getMovieIds()))
				.addObject("movies", unmarshallBasket(
						basket.getMovieIds()));
	}
	
	private static final String REDIRECT_AFTER_ADD = "redirect:/basket";
	@GetMapping("{id}")
	public final String addMovieToBasket(@PathVariable final long id) {
		final Optional<Movie> movie = movieService.read(id);
		
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
	@GetMapping("/customer/{id}")
	public final ModelAndView showReservation(@PathVariable final long id) {
		final ModelAndView modelAndView = new ModelAndView(VIEW_RESERVATION);
		
		customerService.read(id).ifPresent(customer -> 
			modelAndView.addObject("customer", customer));
		
		return modelAndView
				.addObject(new ReservationConfirmationForm())
				.addObject("genres", genreService.findAll());
	}
	
	private static final String REDIRECT_AFTER_CONFIRMATION = "redirect:/reservation/status";
	@PostMapping(path = "/customer")
	public final String makeReservation(
			final ReservationConfirmationForm form,
			final BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return REDIRECT_AFTER_CONFIRMATION;
		
		return REDIRECT_AFTER_CONFIRMATION;
	}
	
	private static final String VIEW_DISPLAY_RESULT = "status";
	@GetMapping("/status")
	public final ModelAndView showReservationStatus() {
		return new ModelAndView(VIEW_DISPLAY_RESULT);
	}
}
