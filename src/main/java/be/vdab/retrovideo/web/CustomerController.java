package be.vdab.retrovideo.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.services.CustomerService;
import be.vdab.retrovideo.services.GenreService;
import be.vdab.retrovideo.web.forms.CustomerSearchForm;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	
	private static final Logger LOGGER
	= LoggerFactory.getLogger(CustomerController.class);

	private final GenreService genreService;
	private final CustomerService customerService;
	
	public CustomerController(
			final GenreService genreService,
			final CustomerService customerService) {
		this.genreService = genreService;
		this.customerService = customerService;
	}
	
	private static final String VIEW_CUSTOMERS = "customers";
	@GetMapping
	public final ModelAndView showCustomers() {
		return new ModelAndView(VIEW_CUSTOMERS)
				.addObject("genres", genreService.findAll())
				.addObject(new CustomerSearchForm(""));
	}
	
	private static final String VIEW_CUSTOMERS_LIST = "customers";
	@GetMapping("/list")
	public final ModelAndView viewListing() {
		return new ModelAndView(VIEW_CUSTOMERS_LIST)
				.addObject("genres", genreService.findAll())
				.addObject(new CustomerSearchForm(""))
				.addObject("customers", customerService.findAll());
	}
	
	private static final String VIEW_SEARCH_RESULTS = "customers";
	@PostMapping
	public final ModelAndView searchCustomers(
			@Valid final CustomerSearchForm form,
			final BindingResult bindingResult) {
		if (bindingResult.hasErrors() ||
			form.getSearchString().replaceAll("\\s", "").isEmpty()) {
			LOGGER.error("Validation error in customers.jsp (searchString: " +
					form.getSearchString() + ")");

			bindingResult.reject("noFamilyName");
			
			return new ModelAndView(VIEW_SEARCH_RESULTS)
					.addObject("genres", genreService.findAll());
		}
		
		return new ModelAndView(VIEW_SEARCH_RESULTS)
				.addObject("genres", genreService.findAll())
				.addObject("searchString", form.getSearchString())
				.addObject("customers",
						customerService.findAllBySearchString(
								form.getSearchString()));
	}
}
