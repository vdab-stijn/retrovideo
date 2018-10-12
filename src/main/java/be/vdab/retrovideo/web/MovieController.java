package be.vdab.retrovideo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
	private static final String VIEW_MOVIE = "movie";
	@GetMapping("{id}")
	public final ModelAndView movie(@PathVariable final long id) {
		final ModelAndView modelAndView = new ModelAndView(VIEW_MOVIE);
		
		modelAndView.addObject("genres", genreService.findAll());
		
		movieService.read(id).ifPresent(movie -> 
			modelAndView.addObject("movie", movie));
		
		return modelAndView;
	}
	
	private static final String REDIRECT_AFTER_ADD = "redirect:/";
	@PostMapping
	public final String addMovieToBasket() {
		
		return REDIRECT_AFTER_ADD;
	}
}
