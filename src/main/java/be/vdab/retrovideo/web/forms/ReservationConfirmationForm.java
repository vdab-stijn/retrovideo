package be.vdab.retrovideo.web.forms;

import java.util.List;

public class ReservationConfirmationForm {

	private long customerId;
	private List<Long> movieIds;
	
	public void setCustomerId(final long customerId) {
		this.customerId = customerId;
	}
	
	public long getCustomerId() {
		return customerId;
	}
	
	public void setMovieIds(final List<Long> movieIds) {
		this.movieIds = movieIds;
	}
	
	public List<Long> getMovieIds() {
		return movieIds;
	}
}
