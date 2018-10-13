package be.vdab.retrovideo.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.entities.Movie;
import be.vdab.retrovideo.services.GenreService;
import be.vdab.retrovideo.services.MovieService;
import be.vdab.retrovideo.web.basket.MovieBasket;
import be.vdab.retrovideo.web.basket.MovieBasketForm;

@Controller
@RequestMapping("/basket")
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
	
	private static final String VIEW_MOVIE = "movie";
	@GetMapping("{id}")
	public final ModelAndView movie(@PathVariable final long movieId) {
		final ModelAndView modelAndView = new ModelAndView(VIEW_MOVIE);
		
		modelAndView.addObject("genres", genreService.findAll());
		
		movieService.read(movieId).ifPresent(movie -> 
			modelAndView.addObject("movie", movie));
		
		return modelAndView;
	}
	
	private List<Movie> unmarshallBasket(final List<Long> movieIds) {
		if (movieIds == null || movieIds.size() == 0)
			return new ArrayList<Movie>();
		
		List<Movie> movies = new ArrayList<>(movieIds.size());
		
		for (long movieId : movieIds)
			movieService.read(movieId).ifPresent(movie -> movies.add(movie));
		
		return movies;
	}
	
	private static final String REDIRECT_AFTER_ADD = "redirect:/basket";
	@PostMapping
	public final String addMovieToBasket(final MovieBasketForm form) {
		basket.addMovieId(form.getMovieId());
		
		return REDIRECT_AFTER_ADD;
	}
}
