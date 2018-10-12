package be.vdab.retrovideo.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import be.vdab.retrovideo.entities.Movie;
import be.vdab.retrovideo.repositories.MovieRepository;

@Service
public class DefaultMovieService implements MovieService {

	private final MovieRepository movieRepository;
	
	public DefaultMovieService(final MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	@Override
	public Optional<Movie> read(long movieId) {
		return movieRepository.read(movieId);
	}

	@Override
	public List<Movie> findAll() {
		return movieRepository.findAll();
	}
	
	@Override
	public List<Movie> findAllByGenre(final long genreId) {
		return movieRepository.findAllByGenre(genreId);
	}

	@Override
	public final BigDecimal countTotal(final List<Long> movieIds) {
		return movieRepository.countTotal(movieIds);
	}
}
