package be.vdab.retrovideo.web.basket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.entities.Movie;
import be.vdab.retrovideo.services.GenreService;
import be.vdab.retrovideo.services.MovieService;

@Controller
@RequestMapping("basket")
public class BasketController {

	private final GenreService genreService;
	private final MovieService movieService;
	private final MovieBasket basket;
	
	public BasketController(
			final GenreService genreService,
			final MovieService movieService,
			final MovieBasket basket) {
		this.genreService = genreService;
		this.movieService = movieService;
		this.basket = basket;
	}
	
	private List<Movie> unmarshallBasket(final List<Long> movieIds) {
		List<Movie> movies = new ArrayList<>(movieIds.size());
		
		for (long movieId : movieIds)
			movieService.read(movieId).ifPresent(movie -> movies.add(movie));
		
		return movies;
	}
	
	private static final String VIEW_BASKET = "basket";
	@GetMapping
	public final ModelAndView showBasket() {
		return new ModelAndView(VIEW_BASKET)
				.addObject("genres", genreService.findAll())
				.addObject("total",
						movieService.countTotal(basket.getReservations()))
				.addObject(new MovieBasketForm())
				.addObject("movies", unmarshallBasket(
						basket.getReservations()));
	}
	
	private static final String REDIRECT_AFTER_ADD = "redirect:/basket";
	@PostMapping
	public final String addMovieToBasket(final MovieBasketForm form) {
		basket.addReservation(form.getMovieId());
		
		return REDIRECT_AFTER_ADD;
	}
}
