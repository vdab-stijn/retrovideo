package be.vdab.retrovideo.web;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import be.vdab.retrovideo.web.basket.MovieBasketForm;

public class MovieBasketFormTest {

	private Validator validator;
	
	@Before
	public void before() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void noCheckOK() {
		assertTrue(validator.validateValue(
				MovieBasketForm.class, "movieId", new Long[] {}).isEmpty());
	}
	
	@Test
	public void singleCheckOK() {
		assertTrue(validator.validateValue(
				MovieBasketForm.class, "movieId", new Long[] { 1L }).isEmpty());
	}
	
	@Test
	public void multipleChecksOK() {
		assertTrue(validator.validateValue(
				MovieBasketForm.class, "movieId", new Long[] { 1L, 2L })
				.isEmpty());
	}
}
