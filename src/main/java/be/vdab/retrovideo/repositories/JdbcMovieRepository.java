package be.vdab.retrovideo.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import be.vdab.retrovideo.entities.Genre;
import be.vdab.retrovideo.entities.Movie;

@Repository
public class JdbcMovieRepository implements MovieRepository {

	private final JdbcTemplate template;
	
	private final RowMapper<Movie> movieMapper = (resultSet, rowNum) ->
		new Movie(
				resultSet.getLong("id"),
				new Genre(
						resultSet.getLong("genreId"),
						resultSet.getString("genreName")),
				resultSet.getString("titel"),
				resultSet.getLong("voorraad"),
				resultSet.getLong("gereserveerd"),
				resultSet.getBigDecimal("prijs"));
	
	public JdbcMovieRepository(final JdbcTemplate template) {
		this.template = template;
	}
	
	private static final String QUERY_SELECT_MOVIE
	= "SELECT m.id AS id, " +
			"g.id AS genreId, g.naam AS genreName, " +
			"m.titel AS titel, m.voorraad AS voorraad, " +
			"m.gereserveerd AS gereserveerd, m.prijs AS prijs " +
			"FROM films AS m " +
			"JOIN genres AS g ON m.genreid = g.id " +
			"WHERE m.id = ?";
	@Override
	public Optional<Movie> read(final long id) {
		try {
			return Optional.of(
					template.queryForObject(
							QUERY_SELECT_MOVIE, movieMapper, id));
		}
		catch (final DataAccessException dae) {
			return Optional.empty();
		}
	}private static final String QUERY_SELECT_MOVIES_ALL
	= "SELECT m.id AS id, " +
			"g.id AS genreId, g.naam AS genreName, " +
			"m.titel AS titel, m.voorraad AS voorraad, " +
			"m.gereserveerd AS gereserveerd, m.prijs AS prijs " +
			"FROM films AS m " +
			"JOIN genres AS g ON m.genreid = g.id " +
			"ORDER BY titel";
	@Override
	public List<Movie> findAll() {
		return template.query(QUERY_SELECT_MOVIES_ALL, movieMapper);
	}

	private static final String QUERY_SELECT_MOVIE_BY_GENRE
	= "SELECT m.id AS id, " +
			"g.id AS genreId, g.naam AS genreName, " +
			"m.titel AS titel, m.voorraad AS voorraad, " +
			"m.gereserveerd AS gereserveerd, m.prijs AS prijs " +
			"FROM films AS m " +
			"JOIN genres AS g ON m.genreid = g.id " +
			"WHERE g.id = ? " +
			"ORDER BY titel";
	@Override
	public List<Movie> findAllByGenre(final long genreId) {
		return template.query(
				QUERY_SELECT_MOVIE_BY_GENRE, movieMapper, genreId);
	}
	
	private static final String QUERY_PRICE
	= "SELECT prijs AS price FROM films WHERE id = ?";
	@Override
	public final BigDecimal countTotal(final List<Long> movieIds) {
		long total = 0L;
		for (final long movieId : movieIds)
			total += template.queryForObject(QUERY_PRICE, Long.class, movieId);
		
		return BigDecimal.valueOf(total);
	}
}
