package be.vdab.retrovideo.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import be.vdab.retrovideo.entities.Genre;
import be.vdab.retrovideo.entities.Movie;

@Repository
public class MovieRepositoryJDBC implements MovieRepository {

	private static final Logger LOGGER
	= LoggerFactory.getLogger(MovieRepositoryJDBC.class);
	
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
	
	public MovieRepositoryJDBC(final JdbcTemplate template) {
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
	}
	
	private static final String QUERY_SELECT_MOVIES_ALL
	= "SELECT m.id AS id, " +
			"g.id AS genreId, g.naam AS genreName, " +
			"m.titel AS titel, m.voorraad AS voorraad, " +
			"m.gereserveerd AS gereserveerd, m.prijs AS prijs " +
			"FROM films AS m " +
			"JOIN genres AS g ON m.genreid = g.id " +
			"ORDER BY titel";
	@Override
	public List<Movie> findAll() {
		LOGGER.debug(template.toString());
		LOGGER.debug(movieMapper.toString());
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
	public List<Movie> findAllByGenreId(final long genreId) {
		return template.query(
				QUERY_SELECT_MOVIE_BY_GENRE, movieMapper, genreId);
	}
	
	private static final String QUERY_SELECT_MOVIE_BY_SEARCH_STRING
	= "SELECT m.id AS id, " +
			"g.id AS genreId, g.naam AS genreName, " +
			"m.titel AS titel, m.voorraad AS voorraad, " +
			"m.gereserveerd AS gereserveerd, m.prijs AS prijs " +
			"FROM films AS m " +
			"JOIN genres AS g ON m.genreid = g.id " +
			"WHERE LOWER(titel) LIKE ? " +
			"ORDER BY titel";
	@Override
	public List<Movie> findAllBySearchString(String searchString) {
		return template.query(
				QUERY_SELECT_MOVIE_BY_SEARCH_STRING.replaceFirst(
						"\\?", "'%" + searchString.toLowerCase() + "%'"),
				movieMapper);
	}
	
	private static final String QUERY_PRICE
	= "SELECT SUM(prijs) FROM films WHERE id IN ";
	@Override
	public BigDecimal countTotal(final List<Long> movieIds) {
		BigDecimal total = BigDecimal.ZERO;
		
		if (movieIds == null) return total;
		
		final int size = movieIds.size();
		
		if (size == 0) return total;
		
		String query = QUERY_PRICE + "(";
		for (int i = 0; i < size; i++) {
			query += movieIds.get(i);
			
			if (i < size - 1) query += ",";
		}
		query += ")";
		
		return template.queryForObject(
					query, BigDecimal.class);
	}
}
