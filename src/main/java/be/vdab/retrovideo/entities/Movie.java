package be.vdab.retrovideo.entities;

import java.math.BigDecimal;

public class Movie extends RetroVideoEntity {

	private Genre genre;
	private String title;
	private long stock;
	private long reservations;
	private BigDecimal price;
	
	public Movie(
			final long id,
			final Genre genre,
			final String title,
			final long stock,
			final long reservations,
			final BigDecimal price) {
		setId(id);
		setGenre(genre);
		setTitle(title);
		setStock(stock);
		setReservations(reservations);
		setPrice(price);
	}
	
	public final void setGenre(final Genre genre) {
		this.genre = genre;
	}
	
	public final Genre getGenre() {
		return genre;
	}
	
	public final void setTitle(final String title) {
		this.title = title;
	}
	
	public final String getTitle() {
		return title;
	}
	
	public final void setStock(final long stock) {
		this.stock = stock;
	}
	
	public final long getStock() {
		return stock;
	}
	
	public final void setReservations(final long reservations) {
		this.reservations = reservations;
	}
	
	public final long getReservations() {
		return reservations;
	}
	
	public final void setPrice(final BigDecimal price) {
		this.price = price;
	}
	
	public final BigDecimal getPrice() {
		return price;
	}
	
	public final long getAvailable() {
		return getStock() - getReservations();
	}
	
	public final boolean canBeReserved() {
		return getAvailable() > 0;
	}
}
