package be.vdab.retrovideo.repositories;

import java.util.List;

import be.vdab.retrovideo.entities.Genre;
import be.vdab.retrovideo.entities.Movie;
import be.vdab.retrovideo.entities.Reservation;

public interface MovieRepository {

	Movie read(final long id);
	List<Movie> findAllByGenre(final Genre genre);
	void addReservation(final Reservation reservation);
}
