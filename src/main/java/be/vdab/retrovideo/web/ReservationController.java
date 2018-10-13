package be.vdab.retrovideo.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.services.CustomerService;
import be.vdab.retrovideo.services.GenreService;
import be.vdab.retrovideo.web.forms.ReservationConfirmationForm;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

	private final GenreService genreService;
	private final CustomerService customerService;
	
	public ReservationController(
			final GenreService genreService,
			final CustomerService customerService) {
		this.genreService = genreService;
		this.customerService = customerService;
	}
	
	private static final String VIEW_RESERVATION = "reservation";
	@GetMapping("{id}")
	public final ModelAndView showReservation(@PathVariable final long id) {
		final ModelAndView modelAndView = new ModelAndView(VIEW_RESERVATION);
		
		customerService.read(id).ifPresent(customer -> 
			modelAndView.addObject("customer", customer));
		
		return modelAndView
				.addObject(new ReservationConfirmationForm())
				.addObject("genres", genreService.findAll());
	}
	
	private static final String REDIRECT_AFTER_CONFIRMATION = "redirect:/reservation/status";
	@PostMapping
	public final String makeReservation(
			final ReservationConfirmationForm form,
			final BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return REDIRECT_AFTER_CONFIRMATION;
		
		return REDIRECT_AFTER_CONFIRMATION;
	}
	
	private static final String VIEW_DISPLAY_RESULT = "status";
	@GetMapping("/status")
	public final ModelAndView showReservationStatus() {
		return new ModelAndView(VIEW_DISPLAY_RESULT);
	}
}
