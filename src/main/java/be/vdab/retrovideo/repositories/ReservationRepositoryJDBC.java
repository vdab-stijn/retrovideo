package be.vdab.retrovideo.repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationRepositoryJDBC implements ReservationRepository {
	
	private static final Logger LOGGER
	= LoggerFactory.getLogger(ReservationRepositoryJDBC.class);

	private final JdbcTemplate template;
	
	public ReservationRepositoryJDBC(final JdbcTemplate template) {
		this.template = template;
	}
	
	private static final String QUERY_INSERT_RESERVATION
	= "INSERT INTO reservaties (klantid, filmid, reservatie) " +
			"VALUES (?, ?, NOW())";
	@Override
	public List<Long> reserveMovies(long customerId, List<Long> movieIds) {
		int[] affected = null;
		
		try {
			affected = template.batchUpdate(QUERY_INSERT_RESERVATION,
				new BatchPreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setLong(1,  customerId);
						ps.setLong(2, movieIds.get(i));
					}
					
					@Override
					public int getBatchSize() {
						return movieIds.size();
					}
				});
		}
		catch (final DataAccessException dae) {
			LOGGER.error("Error while creating DB reservation", dae);
		}
		
		if (affected == null ||
			movieIds.size() != affected.length) {
			LOGGER.error("ERROR: input list size != update list size");
			
			return movieIds;
		}
		
		final List<Long> failed = new ArrayList<>();
		for (int i = 0; i < movieIds.size(); i++) {
			LOGGER.debug(i + " (movie: " + movieIds.get(i) + ") - AFFECTED " + affected[i]);
			
			if (affected[i] != 1)
				failed.add(movieIds.get(i));
		}
		
		return failed;
	}
	
	private static final String QUERY_SELECT_EXISTING_RESERVATION
	= "SELECT klantid FROM reservaties " +
			"WHERE klantid = ? AND filmid = ?";
	@Override
	public boolean hasReserved(long customerId, long movieId) {
		try {
			final long id = template.queryForObject(
								QUERY_SELECT_EXISTING_RESERVATION,
								new Long[] { customerId, movieId }, Long.class);
			
			if (id > 0L)
				return true;
		}
		catch (final DataAccessException dae) {}
		
		return false;
	}
}
