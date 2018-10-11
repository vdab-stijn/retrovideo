package be.vdab.retrovideo.entities;

import java.time.LocalDateTime;

public class Reservation {

	private Customer customer;
	private Movie movie;
	private LocalDateTime reservationDate;
	
	public Reservation(
			final Customer customer,
			final Movie movie,
			final LocalDateTime reservationDate) {
		setCustomer(customer);
		setMovie(movie);
		setReservationDate(reservationDate);
	}
	
	public final void setCustomer(final Customer customer) {
		this.customer = customer;
	}
	
	public final Customer getCustomer() {
		return customer;
	}
	
	public final void setMovie(final Movie movie) {
		this.movie = movie;
	}
	
	public final Movie getMovie() {
		return movie;
	}
	
	public final void setReservationDate(final LocalDateTime reservationDate) {
		this.reservationDate = reservationDate;
	}
	
	public final LocalDateTime getReservationDate() {
		return reservationDate;
	}
}
