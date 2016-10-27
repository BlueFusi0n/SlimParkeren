package nl.groep2.cnl.slim_parkeren.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.mongodb.morphia.annotations.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(value = "Cars", noClassnameStored = true)
public class Car extends EntityModel {
	
	@NotEmpty
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
