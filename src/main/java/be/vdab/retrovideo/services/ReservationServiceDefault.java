package be.vdab.retrovideo.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.retrovideo.repositories.ReservationRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class ReservationServiceDefault implements ReservationService {

	private final ReservationRepository repository;
	
	public ReservationServiceDefault(final ReservationRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public List<Long> reserveMovies(
			final long customerId, final List<Long> movieIds) {
		return repository.reserveMovies(customerId, movieIds);
	}

	@Override
	public boolean hasReserved(long customerId, long movieId) {
		return repository.hasReserved(customerId, movieId);
	}
}
