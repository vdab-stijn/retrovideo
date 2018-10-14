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
}
