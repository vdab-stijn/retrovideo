package be.vdab.retrovideo.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import be.vdab.retrovideo.entities.Movie;
import be.vdab.retrovideo.services.MovieService;

@Controller
@RequestMapping("/ajax")
public class AjaxController {
	
	private static final Logger LOGGER
	= LoggerFactory.getLogger(AjaxController.class);

	private final MovieService movieService;
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	public AjaxController(final MovieService movieService) {
		this.movieService = movieService;
	}
	
	private static final String REDIRECT_AJAX = "redirect:/";
	@GetMapping
	public final String respond() {
		LOGGER.debug("AJAX REQUEST with no or incorrect parameters");
		
		return REDIRECT_AJAX;
	}
	
	private static final String VIEW_AJAX = "ajax";
	@GetMapping(params = {"search"})
	public final ModelAndView respond(
			@RequestParam(value = "search") String search) {
		search = search.trim();
		
		LOGGER.debug("AJAX REQUEST: search string: " + search);
		
		try {
			final List<Movie> movies = movieService.findAllBySearchString(search);
			
			LOGGER.info("AJAX REQUEST: movies found: " + movies.size());
			
			return new ModelAndView(VIEW_AJAX)
					.addObject("response",
							objectMapper
							.writerWithDefaultPrettyPrinter()
							.writeValueAsString(movies));
		}
		catch (final Throwable t) {
			LOGGER.error("AJAX REQUEST: error", t);
		}
		
		return new ModelAndView(VIEW_AJAX).addObject("response", null);
	}
}
