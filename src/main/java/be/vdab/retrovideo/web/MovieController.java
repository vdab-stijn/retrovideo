package be.vdab.retrovideo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.repositories.GenreRepository;
import be.vdab.retrovideo.repositories.MovieRepository;

@Controller
@RequestMapping("/movies")
public class MovieController {

	private final GenreRepository genreRepository;
	private final MovieRepository movieRepository;
	
	public MovieController(
			final GenreRepository genreRepository,
			final MovieRepository movieRepository) {
		this.genreRepository = genreRepository;
		this.movieRepository = movieRepository;
	}
	
	private static final String VIEW_MOVIES = "movies";
	@GetMapping
	public final ModelAndView movies() {
		return new ModelAndView(VIEW_MOVIES)
				.addObject("genres", genreRepository.findAll())
				.addObject("movies", movieRepository.findAll());
	}
		
	private static final String VIEW_MOVIES_BY_GENRES = "movies";
	@GetMapping("{id}")
	public final ModelAndView moviesByGenre(@PathVariable final long id) {
		final ModelAndView modelAndView
		= new ModelAndView(VIEW_MOVIES_BY_GENRES)
				.addObject("genres", genreRepository.findAll())
				.addObject("currentGenre", id)
				.addObject("movies", movieRepository.findAllByGenre(id));
		
		genreRepository.read(id).ifPresent(genre ->
				modelAndView.addObject("genre", genre));
		
		return modelAndView;
	}
	
	private static final String VIEW_MOVIE = "movie";
	@GetMapping("/movie/{id}")
	public final ModelAndView movie(final Long movieId) {
		return new ModelAndView(VIEW_MOVIE);
	}
}
