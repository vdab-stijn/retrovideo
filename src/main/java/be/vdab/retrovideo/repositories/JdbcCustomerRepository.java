package be.vdab.retrovideo.repositories;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import be.vdab.retrovideo.entities.Customer;

@Repository
public class JdbcCustomerRepository implements CustomerRepository {

	private final JdbcTemplate template;
	
	private final RowMapper<Customer> customerMapper = (resultSet, rowNum) ->
		new Customer(
				resultSet.getLong("id"),
				resultSet.getString("voornaam"),
				resultSet.getString("familienaam"),
				resultSet.getString("straatNummer"),
				resultSet.getString("postcode"),
				resultSet.getString("gemeente"));
	
	public JdbcCustomerRepository(final JdbcTemplate template) {
		this.template = template;
	}
	
	/*
	 * 
	 * READ ALL CUSTOMERS WITH A SEARCH STRING IN THEIR LAST NAME
	 */
	private static final String QUERY_SELECT_BY_SEARCH_STRING
	= "SELECT id, voornaam, familienaam, straatNummer, postcode, gemeente " +
			"FROM klanten WHERE familienaam LIKE ?";
	@Override
	public List<Customer> findAllBySearchString(String searchString) {
		return template.query(QUERY_SELECT_BY_SEARCH_STRING, customerMapper);
	}

}
