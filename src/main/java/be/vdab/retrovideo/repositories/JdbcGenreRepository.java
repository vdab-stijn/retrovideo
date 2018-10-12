package be.vdab.retrovideo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import be.vdab.retrovideo.entities.Genre;

@Repository
public class JdbcGenreRepository implements GenreRepository {

	private final JdbcTemplate template;
	
	private final RowMapper<Genre> genreMapper = (resultSet, rowNum) ->
		new Genre(
				resultSet.getLong("id"),
				resultSet.getString("naam"));
	
	public JdbcGenreRepository(final JdbcTemplate template) {
		this.template = template;
	}
	
	/*
	 * 
	 * READ SPECIFIC GENRE
	 * 
	 */
	private static final String QUERY_SELECT_GENRE
	= "SELECT id, naam FROM genres WHERE id = ?";
	@Override
	public Optional<Genre> read(long id) {
		try {
			return Optional.of(
				template.queryForObject(QUERY_SELECT_GENRE, genreMapper, id));
		}
		catch (final DataAccessException dae) {
			return Optional.empty();
		}
	}

	/*
	 * 
	 * READ ALL GENRES
	 * 
	 */
	private static final String QUERY_SELECT_ALL_GENRES
	= "SELECT id, naam FROM genres ORDER BY naam";
	@Override
	public List<Genre> findAll() {
		return template.query(QUERY_SELECT_ALL_GENRES,  genreMapper);
	}
}
