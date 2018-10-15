package be.vdab.retrovideo.web.basket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class DefaultMovieBasket implements Serializable, MovieBasket {

	/* Implements Serializable. */
	private static final long serialVersionUID = -2259179673057966066L;
	
	private final List<Long> movieIds;
	
	private long customerId;
	
	private List<Long> intermittentlyReservedMovieIds = new ArrayList<>();
	private List<Long> alreadyReservedMovieIds = new ArrayList<>();
	private List<Long> stockUpdateFailureMovieIds = new ArrayList<>();
	private List<Long> reservationCreationFailureMovieIds = new ArrayList<>();
	
	public DefaultMovieBasket() {
		movieIds = new ArrayList<>();
	}

	@Override
	public void addMovieId(final long movieId) {
		if (!movieIds.contains(movieId))
			movieIds.add(movieId);
	}
	
	@Override
	public void deleteMovieId(final long movieId) {
		if (movieIds.contains(movieId))
			movieIds.remove(movieId);
	}

	@Override
	public List<Long> getMovieIds() {
		return movieIds;
	}
	
	@Override
	public void setCustomerId(final long customerId) {
		this.customerId = customerId;
	}
	
	@Override
	public long getCustomerId() {
		return customerId;
	}
	
	@Override
	public boolean hasCustomerId() {
		return customerId > 0L;
	}

	@Override
	public void setIntermittentlyReservedMovieIds(
			final List<Long> intermittentlyReservedMovieIds) {
		this.intermittentlyReservedMovieIds = intermittentlyReservedMovieIds;
	}
	
	@Override
	public List<Long> getIntermittentlyReservedMovieIds() {
		return intermittentlyReservedMovieIds;
	}

	@Override
	public void setAlreadyReservedMovieIds(List<Long> alreadyReservedMovieIds) {
		this.alreadyReservedMovieIds = alreadyReservedMovieIds;
	}

	@Override
	public List<Long> getAlreadyReservedMovieIds() {
		return alreadyReservedMovieIds;
	}

	@Override
	public void setStockUpdateFailureMovieIds(
			final List<Long> stockUpdateFailureMovieIds) {
		this.stockUpdateFailureMovieIds = stockUpdateFailureMovieIds;
	}
	
	@Override
	public List<Long> getStockUpdateFailureMovieIds() {
		return stockUpdateFailureMovieIds;
	}
	
	@Override
	public void setReservationCreationFailureMovieIds(
			final List<Long> reservationCreationFailureMovieIds) {
		this.reservationCreationFailureMovieIds
		= reservationCreationFailureMovieIds;
	}
	
	@Override
	public List<Long> getReservationCreationFailureMovieIds() {
		return reservationCreationFailureMovieIds;
	}

	@Override
	public List<Long> getSuccessfullyReservedMovieIds() {
		final List<Long> success = new ArrayList<>();
		for (final long movieId : movieIds)
			if ((intermittentlyReservedMovieIds == null ||
					!intermittentlyReservedMovieIds.contains(movieId)) &&
				(alreadyReservedMovieIds == null ||
					!alreadyReservedMovieIds.contains(movieId)) &&
				(stockUpdateFailureMovieIds == null ||
						!stockUpdateFailureMovieIds.contains(movieId)) &&
				(reservationCreationFailureMovieIds == null ||
						!reservationCreationFailureMovieIds.contains(movieId)))
				success.add(movieId);
		
		return success;
	}
	
	@Override
	public boolean success() {
		return (intermittentlyReservedMovieIds == null ||
					intermittentlyReservedMovieIds.size() == 0) &&
				(alreadyReservedMovieIds == null ||
					alreadyReservedMovieIds.size() == 0) &&
				(stockUpdateFailureMovieIds == null ||
					stockUpdateFailureMovieIds.size() == 0) &&
				(reservationCreationFailureMovieIds == null ||
					reservationCreationFailureMovieIds.size() == 0);
				
	}
	
	@Override
	public void clearBasket() {
		movieIds.clear();
		intermittentlyReservedMovieIds.clear();
		alreadyReservedMovieIds.clear();
		stockUpdateFailureMovieIds.clear();
		reservationCreationFailureMovieIds.clear();
		
		customerId = -1L;
	}
}
