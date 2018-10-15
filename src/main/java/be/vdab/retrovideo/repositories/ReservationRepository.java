package be.vdab.retrovideo.repositories;

import java.util.List;

public interface ReservationRepository {

	public List<Long> reserveMovies(
			final long customerId, final List<Long> movieIds);
	public boolean hasReserved(final long customerId, final long movieId);
}
