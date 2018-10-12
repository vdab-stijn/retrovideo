package be.vdab.retrovideo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.services.GenreService;
import be.vdab.retrovideo.services.MovieService;

@Controller
@RequestMapping("/")
public class IndexController {

	private final GenreService genreService;
	private final MovieService movieService;
	
	public IndexController(
			final GenreService genreService,
			final MovieService movieService) {
		this.genreService = genreService;
		this.movieService = movieService;
	}
	
	private static final String VIEW_INDEX = "index";
	@GetMapping
	public final ModelAndView index() {
		return new ModelAndView(VIEW_INDEX)
				.addObject("genres", genreService.findAll());
	}
	
	private static final String VIEW_MOVIES_BY_GENRES = "index";
	@GetMapping("{id}")
	public final ModelAndView moviesByGenre(@PathVariable final long id) {
		final ModelAndView modelAndView
		= new ModelAndView(VIEW_MOVIES_BY_GENRES)
				.addObject("genres", genreService.findAll())
				.addObject("currentGenre", id)
				.addObject("movies", movieService.findAllByGenre(id));
		
		genreService.read(id).ifPresent(genre ->
				modelAndView.addObject("genre", genre));
		
		return modelAndView;
	}
}
