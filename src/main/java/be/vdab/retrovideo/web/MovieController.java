package be.vdab.retrovideo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.repositories.MovieRepository;

@Controller
@RequestMapping("/movies")
public class MovieController {

	private final MovieRepository movieRepository;
	
	public MovieController(final MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	private static final String VIEW_MOVIES = "movies";
	@GetMapping
	public final ModelAndView movies() {
		return new ModelAndView(VIEW_MOVIES)
				.addObject("movies", movieRepository.findAll());
	}
		
	private static final String VIEW_MOVIES_BY_GENRES = "movies";
	@GetMapping("{id}")
	public final ModelAndView moviesByGenre(final Long genreId) {
		return new ModelAndView(VIEW_MOVIES_BY_GENRES)
				.addObject("movies", movieRepository.findAllByGenre(genreId));
	}
	
	private static final String VIEW_MOVIE = "movie";
	@GetMapping("/movie/{id}")
	public final ModelAndView movie(final Long movieId) {
		return new ModelAndView(VIEW_MOVIE);
	}
}
