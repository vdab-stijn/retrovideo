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
	
	private final List<Long> movieIds = new ArrayList<>();

	@Override
	public final void addReservation(long movieId) {
		movieIds.add(movieId);
	}
	
	public final void removeReservation(final long movieId) {
		
	}

	@Override
	public final List<Long> getReservations() {
		return movieIds;
	}
}
