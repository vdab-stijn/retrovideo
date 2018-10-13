package be.vdab.retrovideo.web.basket;

import javax.validation.constraints.NotNull;

public class MovieBasketForm {

	@NotNull.List(value = { @NotNull })
	private Long[] movieId;
	
	public final void setMovieId(final Long[] movieId) {
		this.movieId = movieId;
	}
	
	public final Long[] getMovieId() {
		return movieId;
	}
}
