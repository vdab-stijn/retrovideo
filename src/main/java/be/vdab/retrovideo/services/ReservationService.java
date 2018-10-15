package be.vdab.retrovideo.services;

import java.util.List;

public interface ReservationService {

	public List<Long> reserveMovies(
			final long customerId, final List<Long> movieIds);
	public boolean hasReserved(final long customerId, final long movieId);
}
