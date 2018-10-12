package be.vdab.retrovideo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.repositories.GenreRepository;

@Controller
@RequestMapping("/")
public class IndexController {

	private final GenreRepository genreRepository;
	
	public IndexController(
			final GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}
	
	private static final String VIEW_INDEX = "index";
	@GetMapping
	public final ModelAndView index() {
		return new ModelAndView(VIEW_INDEX)
				.addObject("genres", genreRepository.findAll());
	}
}
