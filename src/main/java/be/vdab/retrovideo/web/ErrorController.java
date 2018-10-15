package be.vdab.retrovideo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.services.GenreService;

@Controller
@RequestMapping("/error")
public class ErrorController {
	
	private final GenreService genreService;
	private final ErrorAttributes errorAttributes;
	
	@Autowired
	public ErrorController(
			final GenreService genreService,
			final ErrorAttributes errorAttributes) {
		this.genreService = genreService;
		this.errorAttributes = errorAttributes;
	}

	private static final String VIEW_ERROR = "error";
	@GetMapping
	public final ModelAndView error(final WebRequest request) {
		final ModelAndView modelAndView = new ModelAndView(VIEW_ERROR);
		final Throwable throwable = errorAttributes.getError(request);
		
		modelAndView.addObject("genres", genreService.findAll());
		
		if (throwable == null) return modelAndView;
		
		return modelAndView
				.addObject("cause", throwable.getCause())
				.addObject("message", throwable.getMessage())
				.addObject("trace", throwable.getStackTrace());
	}
}
