package be.vdab.retrovideo.repositories;

import java.util.List;

import be.vdab.retrovideo.entities.Customer;

public interface CustomerRepository {

	List<Customer> findAllBySearchString(final String searchString);
}
