package be.vdab.retrovideo.web.basket;

import java.util.List;

public interface MovieBasket {

	public void addMovieId(final long movieId);
	public void deleteMovieId(final long movieId);
	
	public List<Long> getMovieIds();
	
	public void setCustomerId(final long customerId);
	public long getCustomerId();
	public boolean hasCustomerId();
	
	public void setIntermittentlyReservedMovieIds(
			final List<Long> intermittentlyReservedMovieIds);
	public List<Long> getIntermittentlyReservedMovieIds();
	public void setAlreadyReservedMovieIds(
			final List<Long> alreadyReservedMovieIds);
	public List<Long> getAlreadyReservedMovieIds();
	public void setStockUpdateFailureMovieIds(
			final List<Long> stockUpdateFailureMovieIds);
	public List<Long> getStockUpdateFailureMovieIds();
	public void setReservationCreationFailureMovieIds(
			final List<Long> reservationCreationFailureMovieIds);
	public List<Long> getReservationCreationFailureMovieIds();
	public List<Long> getSuccessfullyReservedMovieIds();
	
	public boolean success();
	
	public void clearBasket();
}
