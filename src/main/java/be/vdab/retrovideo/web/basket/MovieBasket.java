package be.vdab.retrovideo.web.basket;

import java.util.List;

public interface MovieBasket {

	public void addReservation(final long movieId);
	public void removeReservation(final long movieId);
	
	public List<Long> getReservations();
}
