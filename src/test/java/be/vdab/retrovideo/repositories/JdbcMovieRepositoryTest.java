package be.vdab.retrovideo.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.retrovideo.entities.Movie;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(JdbcMovieRepository.class)
public class JdbcMovieRepositoryTest
extends AbstractTransactionalJUnit4SpringContextTests {
	
	private static final String MOVIES = "films";
	private static final String KNOWN_GENRE_NAME = "Avontuur";
	
	@Autowired
	private JdbcMovieRepository repository;
	
	private static final String SELECT_MOVIE_ZORRO_ID
	= "SELECT id FROM films WHERE titel = 'Zorro'";
	private final long getIdOfKnownMovie() {
		return super.jdbcTemplate.queryForObject(
				SELECT_MOVIE_ZORRO_ID, Long.class);
	}
	
	private static final String SELECT_GENRE_ID
	= "SELECT id FROM genres WHERE naam = '" + KNOWN_GENRE_NAME + "'";
	private final long getIdOfKnownGenre() {
		return super.jdbcTemplate.queryForObject(
				SELECT_GENRE_ID, Long.class);
	}
	
	@Test
	public final void readReturnsAMovie() {
		final long id = getIdOfKnownMovie();
		final Optional<?> optional = repository.read(id);
		
		// Movie is found
		assertTrue(optional.isPresent());
		// Returns a Movie object
		assertTrue(optional.get() instanceof Movie);
		
		final Movie movie = (Movie)optional.get();
		// Expected id
		assertEquals(movie.getId(), id);
		// Genre member is loaded correctly
		assertEquals(movie.getGenre().getName(), KNOWN_GENRE_NAME);
	}
	
	@Test
	public final void findAllWorks() {
		final List<Movie> movies = repository.findAll();
		
		// Movies are found
		assertTrue(movies.size() > 0);
		// Number of movies found is as expected
		assertEquals(movies.size(), super.countRowsInTable(MOVIES));
	}
	
	@Test
	public final void findAllByGenreWorks() {
		final long genreId = getIdOfKnownGenre();
		final List<Movie> movies = repository.findAllByGenre(genreId);
		
		// Movies are found
		assertTrue(movies.size() > 0);
		// Number of movies found is as expected
		assertEquals(movies.size(), super.countRowsInTableWhere(
				MOVIES, "genreid = " + genreId));
	}
}
