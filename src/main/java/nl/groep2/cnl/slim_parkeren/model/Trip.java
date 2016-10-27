package nl.groep2.cnl.slim_parkeren.model;

import java.util.*;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.mongodb.morphia.annotations.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(value = "Trips", noClassnameStored = true)
public class Trip extends EntityModel{
	
	
	
	@NotEmpty
	@JsonProperty("FlightNr1")
	private String FlightNr1;
	
	@NotEmpty
	@JsonProperty("FlightNr2")
	private String FlightNr2;

	@NotNull
	@JsonProperty("ArrivalDate")
	private Date ArrivalDate;

	@NotNull
	@JsonProperty("DepartureDate")
	private Date DepartureDate;
	
	
	public String getFlightNr1() {
		return FlightNr1;
	}
	public void setFlightNr1(String flightNr1) {
		FlightNr1 = flightNr1;
	}
	public String getFlightNr2() {
		return FlightNr2;
	}
	public void setFlightNr2(String flightNr2) {
		FlightNr2 = flightNr2;
	}
	public Date getArrivalDate() {
		return ArrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		ArrivalDate = arrivalDate;
	}
	public Date getDepartureDate() {
		return DepartureDate;
	}
	public void setDepartureDate(Date departureDate) {
		DepartureDate = departureDate;
	}
}
