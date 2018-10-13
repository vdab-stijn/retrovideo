package be.vdab.retrovideo.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import be.vdab.retrovideo.entities.Movie;

public interface MovieService {

	public Optional<Movie> read(final long movieId);
	public List<Movie> findAll();
	public List<Movie> findAllByGenreId(final long genreId);
	public List<Movie> findAllBySearchString(final String searchString);
	
	public BigDecimal countTotal(final List<Long> movieIds);
}
