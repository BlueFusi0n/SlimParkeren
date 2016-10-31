package nl.groep2.cnl.slim_parkeren.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.mongodb.morphia.annotations.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(value = "Cars", noClassnameStored = true)
public class Car extends EntityModel {
	
	@NotEmpty
	@Pattern(regexp="^(?>[A-Z]{2}|\\d\\d)-(?>[A-Z]{2}|\\d\\d)-(?<!\\d\\d-\\d\\d-)\\d\\d$|^(?>[A-Z]{2}|\\d\\d)-(?>[A-Z]{2}|\\d\\d)-(?<![A-Z]{2}-[A-Z]{2}-)[A-Z]{2}$|^\\d\\d-[A-Z]{3}-\\d$", message=": Vul een geldig kenteken in.")
	@JsonProperty("LicensePlate")
	private String licensePlate;
	@NotEmpty
	@JsonProperty("Brand")
	private String brand;
	@NotEmpty
	@JsonProperty("Color")
	private String color;
	@NotEmpty
	@JsonProperty("Type")
	private String type;
	private String customerId;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
