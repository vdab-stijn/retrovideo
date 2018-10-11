package be.vdab.retrovideo.repositories;

import java.util.List;
import java.util.Optional;

import be.vdab.retrovideo.entities.Genre;

public interface GenreRepository {

	Optional<Genre> read(final long id);
	List<Genre> findAll();
}
