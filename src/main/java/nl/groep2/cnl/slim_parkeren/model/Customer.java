package nl.groep2.cnl.slim_parkeren.model;

import java.util.ArrayList;
import java.util.List;

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
	private List<Car> cars;
	
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
	public List<Car> getCars() {
		return cars;
	}
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}	
	public void addCar(Car car){
		if(this.cars == null)
			cars = new ArrayList<Car>();
		cars.add(car);
	}
}
