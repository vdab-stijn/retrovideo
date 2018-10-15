package be.vdab.retrovideo.repositories;

import java.util.List;
import java.util.Optional;

import be.vdab.retrovideo.entities.Customer;

public interface CustomerRepository {

	Optional<Customer> read(final long customerId);
	List<Customer> findAll();
	List<Customer> findAllBySearchString(final String searchString);
}
