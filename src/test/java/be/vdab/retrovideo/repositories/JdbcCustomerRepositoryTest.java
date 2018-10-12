package be.vdab.retrovideo.repositories;

import static org.junit.Assert.assertEquals;
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

import be.vdab.retrovideo.entities.Customer;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(JdbcCustomerRepository.class)
public class JdbcCustomerRepositoryTest
extends AbstractTransactionalJUnit4SpringContextTests {

	private static final String CUSTOMERS = "klanten";
	
	private static final String SEARCH_STRING = "man";
	
	@Autowired
	private JdbcCustomerRepository repository;
	
	@Test
	public void findAllBySearchStringReturnsAListOfCustomerObjects() {
		final List<?> customers
		= repository.findAllBySearchString(SEARCH_STRING);
		
		for (Object customer : customers) {
			assertTrue(customer instanceof Customer);
		}
	}
	
	@Test
	public void knownSearchResultIsConfirmedByFindAllBySearchString() {
		final List<Customer> customers
		= repository.findAllBySearchString(SEARCH_STRING);
		
		assertEquals(customers.size(), super.countRowsInTableWhere(
				CUSTOMERS, "familienaam LIKE '%" + SEARCH_STRING + "%'"));
	}
}
