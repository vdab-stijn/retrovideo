package be.vdab.retrovideo.services;

import java.util.List;
import java.util.Optional;

import be.vdab.retrovideo.entities.Genre;

public interface GenreService {

	Optional<Genre> read(final long id);
	List<Genre> findAll();
}
