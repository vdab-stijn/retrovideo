package be.vdab.retrovideo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/error")
public class ErrorController {
	
	private final ErrorAttributes errorAttributes;
	
	@Autowired
	public ErrorController(final ErrorAttributes errorAttributes) {
		this.errorAttributes = errorAttributes;
	}

	private static final String VIEW_ERROR = "error";
	@GetMapping
	public final ModelAndView error(final WebRequest request) {
		final Throwable throwable = errorAttributes.getError(request);
		
		if (throwable == null) return new ModelAndView(VIEW_ERROR);
		
		return new ModelAndView(VIEW_ERROR)
				.addObject("cause", throwable.getCause())
				.addObject("message", throwable.getMessage())
				.addObject("trace", throwable.getStackTrace());
	}
}
