package nl.groep2.cnl.slim_parkeren.model;

import java.util.*;

public class Trip extends EntityModel{
	
	private String FlightNr1, FlightNr2;
	private Date ArrivalDate, DepartureDate;
	
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
