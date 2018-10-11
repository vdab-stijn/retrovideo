package be.vdab.retrovideo.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.retrovideo.entities.Genre;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(JdbcGenreRepository.class)
public class JdbcGenreRepositoryTest
extends AbstractTransactionalJUnit4SpringContextTests {

	private static final String GENRES = "genres";
	
	@Autowired
	private JdbcGenreRepository repository;
	
	@Test
	public void readUnknownGenre() {
		assertFalse(repository.read(-1).isPresent());
	}
	
	@Test
	public void findAll() {
		final List<Genre> genres = repository.findAll();
		
		// The expected number of genres are returned
		assertEquals(genres.size(), super.countRowsInTable(GENRES));
		
		// The genres are sorted alphabetically
		String previousName = "";
		for (final Genre genre : genres) {
			assertTrue(genre.getName().compareTo(previousName) > 0);
			
			previousName = genre.getName();
		}
	}
}
