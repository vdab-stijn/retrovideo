package be.vdab.retrovideo.entities;

import org.apache.commons.text.WordUtils;

public class Customer extends RetroVideoEntity {

	private String firstName;
	private String lastName;
	private String streetNumber;
	private String postalCode;
	private String municipality;
	
	public Customer(
			final long id,
			final String firstName,
			final String lastName,
			final String streetNumber,
			final String postalCode,
			final String municipality) {
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setStreetNumber(streetNumber);
		setPostalCode(postalCode);
		setMunicipality(municipality);
	}
	
	public final void setFirstName(final String firstName) {
		this.firstName = firstName;
	}
	
	public final String getFirstName() {
		return firstName;
	}
	
	public final void setLastName(final String lastName) {
		this.lastName = lastName;
	}
	
	public final String getLastName() {
		return lastName;
	}
	
	public final void setStreetNumber(final String streetNumber) {
		this.streetNumber = streetNumber;
	}
	
	public final String getStreetNumber() {
		return streetNumber;
	}
	
	public final void setPostalCode(final String postalCode) {
		this.postalCode = postalCode;
	}
	
	public final String getPostalCode() {
		return postalCode;
	}
	
	public final void setMunicipality(final String municipality) {
		this.municipality = municipality;
	}
	
	public final String getMunicipality() {
		return municipality;
	}
	
	public final String getName() {
		return WordUtils.capitalize(getFirstName() + " " + getLastName());
	}
}
