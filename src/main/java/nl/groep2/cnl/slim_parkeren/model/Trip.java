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
	private String flightNr1;
	
	@NotEmpty
	@JsonProperty("FlightNr2")
	private String flightNr2;

	@NotNull
	@JsonProperty("ArrivalDate")
	private Date arrivalDate;

	@NotNull
	@JsonProperty("DepartureDate")
	private Date departureDate;

	public String getFlightNr1() {
		return flightNr1;
	}

	public void setFlightNr1(String flightNr1) {
		this.flightNr1 = flightNr1;
	}

	public String getFlightNr2() {
		return flightNr2;
	}

	public void setFlightNr2(String flightNr2) {
		this.flightNr2 = flightNr2;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
}
