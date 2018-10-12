package be.vdab.retrovideo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.services.GenreService;
import be.vdab.retrovideo.services.MovieService;

@Controller
@RequestMapping("/movies")
public class MoviesController {

	private final GenreService genreService;
	private final MovieService movieService;
	
	public MoviesController(
			final GenreService genreService,
			final MovieService movieService) {
		this.genreService = genreService;
		this.movieService = movieService;
	}
	
	private static final String VIEW_MOVIES = "movies";
	@GetMapping
	public final ModelAndView movies() {
		return new ModelAndView(VIEW_MOVIES)
				.addObject("genres", genreService.findAll())
				.addObject("movies", movieService.findAll());
	}
}
