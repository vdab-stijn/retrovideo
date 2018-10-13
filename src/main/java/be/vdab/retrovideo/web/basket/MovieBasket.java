package be.vdab.retrovideo.web.basket;

import java.util.List;

public interface MovieBasket {

	public void addMovieId(final long movieId);
	public void deleteMovieId(final long movieId);
	
	public List<Long> getMovieIds();
}
