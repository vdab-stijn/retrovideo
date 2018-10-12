package be.vdab.retrovideo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import be.vdab.retrovideo.entities.Genre;
import be.vdab.retrovideo.repositories.GenreRepository;

@Service
public class DefaultGenreService implements GenreService {

	public final GenreRepository genreRepository;
	
	public DefaultGenreService(final GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}
	
	@Override
	public Optional<Genre> read(long id) {
		return genreRepository.read(id);
	}

	@Override
	public List<Genre> findAll() {
		return genreRepository.findAll();
	}

}
