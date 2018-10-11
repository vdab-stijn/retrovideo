package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.entities.Reservation;

public interface ReservationRepository {

	void create(final Reservation reservation);
}
