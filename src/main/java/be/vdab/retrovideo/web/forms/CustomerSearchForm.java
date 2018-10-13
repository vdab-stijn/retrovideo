package be.vdab.retrovideo.web.forms;

import javax.validation.constraints.NotEmpty;

public class CustomerSearchForm {

	@NotEmpty
	private String searchString;
	
	public CustomerSearchForm(final String searchString) {
		setSearchString(searchString);
	}
	
	public void setSearchString(final String searchString) {
		this.searchString = searchString;
	}
	
	public String getSearchString() {
		return searchString;
	}
}
