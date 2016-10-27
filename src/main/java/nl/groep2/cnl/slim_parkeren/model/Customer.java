package nl.groep2.cnl.slim_parkeren.model;

import org.hibernate.validator.constraints.*;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(value = "Customers", noClassnameStored = true)
public class Customer extends EntityModel {
	
	@NotEmpty
	@JsonProperty("FirstName")
	private String firstName;
	@NotEmpty
	@JsonProperty("LastName")
	private String lastName;
	@NotEmpty
	@JsonProperty("Phone")
	private String phone;
	@NotEmpty
	@Email
	@JsonProperty("Email")
	private String email;
	@Embedded
	@JsonProperty("Car")
	private Car car;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
}
