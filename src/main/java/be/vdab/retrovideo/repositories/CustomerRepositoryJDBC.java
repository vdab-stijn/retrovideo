package be.vdab.retrovideo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import be.vdab.retrovideo.entities.Customer;

@Repository
public class CustomerRepositoryJDBC implements CustomerRepository {

	private final JdbcTemplate template;
	
	private final RowMapper<Customer> customerMapper = (resultSet, rowNum) ->
		new Customer(
				resultSet.getLong("id"),
				resultSet.getString("voornaam"),
				resultSet.getString("familienaam"),
				resultSet.getString("straatNummer"),
				resultSet.getString("postcode"),
				resultSet.getString("gemeente"));
	
	public CustomerRepositoryJDBC(final JdbcTemplate template) {
		this.template = template;
	}
	
	/*
	 * READ A SPECIFIC CUSTOMER
	 */
	private static final String QUERY_SELECT_BY_ID
	= "SELECT id, voornaam, familienaam, straatNummer, postcode, gemeente " +
			"FROM klanten WHERE id = ? ";
	public Optional<Customer> read(final long customerId) {
		return Optional.of(template.queryForObject(
				QUERY_SELECT_BY_ID, customerMapper, customerId));
	}
	
	/*
	 * 
	 * READ ALL CUSTOMERS
	 */
	private static final String QUERY_SELECT_ALL
	= "SELECT id, voornaam, familienaam, straatNummer, postcode, gemeente " +
			"FROM klanten " +
			"ORDER BY familienaam, voornaam";
	@Override
	public List<Customer> findAll() {
		return template.query(QUERY_SELECT_ALL, customerMapper);
	}
	
	/*
	 * 
	 * READ ALL CUSTOMERS WITH A SEARCH STRING IN THEIR LAST NAME
	 */
	private static final String QUERY_SELECT_BY_SEARCH_STRING
	= "SELECT id, voornaam, familienaam, straatNummer, postcode, gemeente " +
			"FROM klanten WHERE familienaam LIKE ? " +
			"ORDER BY familienaam, voornaam";
	@Override
	public List<Customer> findAllBySearchString(String searchString) {
		return template.query(
				QUERY_SELECT_BY_SEARCH_STRING,
				new String[] { "%" + searchString + "%" },
				customerMapper);
	}
}
