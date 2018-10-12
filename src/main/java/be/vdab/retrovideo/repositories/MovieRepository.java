package be.vdab.retrovideo.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import be.vdab.retrovideo.entities.Movie;

public interface MovieRepository {

	Optional<Movie> read(final long id);
	
	List<Movie> findAll();
	List<Movie> findAllByGenre(final long genreId);
	
	BigDecimal countTotal(final List<Long> movieIds);
}
