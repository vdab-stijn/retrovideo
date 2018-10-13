package be.vdab.retrovideo.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.retrovideo.entities.Movie;
import be.vdab.retrovideo.repositories.MovieRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class MovieServiceDefault implements MovieService {

	private final MovieRepository movieRepository;
	
	public MovieServiceDefault(final MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	@Override
	public final Optional<Movie> read(final long movieId) {
		return movieRepository.read(movieId);
	}

	@Override
	public final List<Movie> findAll() {
		return movieRepository.findAll();
	}
	
	@Override
	public final List<Movie> findAllByGenreId(final long genreId) {
		return movieRepository.findAllByGenreId(genreId);
	}

	@Override
	public final BigDecimal countTotal(final List<Long> movieIds) {
		return movieRepository.countTotal(movieIds);
	}

	@Override
	public final List<Movie> findAllBySearchString(final String searchString) {
		return movieRepository.findAllBySearchString(searchString);
	}
}
