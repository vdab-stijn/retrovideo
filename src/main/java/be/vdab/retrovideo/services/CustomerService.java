package be.vdab.retrovideo.services;

import java.util.List;
import java.util.Optional;

import be.vdab.retrovideo.entities.Customer;

public interface CustomerService {

	Optional<Customer> read(final long customerId);
	List<Customer> findAllBySearchString(final String searchString);
}
