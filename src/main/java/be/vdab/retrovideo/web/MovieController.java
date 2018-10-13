package be.vdab.retrovideo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import be.vdab.retrovideo.services.GenreService;
import be.vdab.retrovideo.services.MovieService;

@Controller
@RequestMapping("/movie")
public class MovieController {

	private final GenreService genreService;
	private final MovieService movieService;
	
	public MovieController(
			final GenreService genreService,
			final MovieService movieService) {
		this.genreService = genreService;
		this.movieService = movieService;
	}
	
	private static final String REDIRECT_AFTER_ADD = "redirect:/";
	@PostMapping
	public final String addMovieToBasket() {
		
		return REDIRECT_AFTER_ADD;
	}
}
