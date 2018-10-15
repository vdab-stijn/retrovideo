package be.vdab.retrovideo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.retrovideo.entities.Customer;
import be.vdab.retrovideo.repositories.CustomerRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class CustomerServiceDefault implements CustomerService {

	private final CustomerRepository customerRepository;
	
	public CustomerServiceDefault(final CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public Optional<Customer> read(final long customerId) {
		return customerRepository.read(customerId);
	}
	
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}
	
	public List<Customer> findAllBySearchString(final String searchString) {
		return customerRepository.findAllBySearchString(searchString);
	}
}
