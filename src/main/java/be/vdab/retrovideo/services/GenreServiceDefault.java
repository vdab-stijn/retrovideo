package be.vdab.retrovideo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.retrovideo.entities.Genre;
import be.vdab.retrovideo.repositories.GenreRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class GenreServiceDefault implements GenreService {

	public final GenreRepository genreRepository;
	
	public GenreServiceDefault(final GenreRepository genreRepository) {
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
