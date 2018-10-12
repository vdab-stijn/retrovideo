package be.vdab.retrovideo.repositories;

import java.util.List;
import java.util.Optional;

import be.vdab.retrovideo.entities.Genre;
import be.vdab.retrovideo.entities.Movie;
import be.vdab.retrovideo.entities.Reservation;

public interface MovieRepository {

	Optional<Movie> read(final long id);
	List<Movie> findAllByGenre(final long genreId);
	void addReservation(final Reservation reservation);
}
