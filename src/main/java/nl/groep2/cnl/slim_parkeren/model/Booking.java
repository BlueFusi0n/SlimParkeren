package nl.groep2.cnl.slim_parkeren.model;

import java.util.Currency;

import org.hibernate.validator.constraints.NotEmpty;
import org.mongodb.morphia.annotations.Embedded;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Booking extends EntityModel  {
	@Embedded
	@NotEmpty
	@JsonProperty("Trip")
	private Trip trip;
	@Embedded
	@NotEmpty
	@JsonProperty("Customer")
	private Customer customer;
	@Embedded
	@NotEmpty
	@JsonProperty("Car")
	private Car car;
	@Embedded
	@NotEmpty
	@JsonProperty("Payment")
	private Payment payment;
	@Embedded
	@JsonProperty("Discount")
	private Discount discount;
	
	@NotEmpty
	@JsonProperty("BookingNr")
	private String bookingNr;
	@JsonProperty("Comments")
	private String comments;
	@NotEmpty
	@JsonProperty("Price")
	private Currency price;
	@NotEmpty
	@JsonProperty("Btw")
	private Currency btw;
	@NotEmpty
	@JsonProperty("Days")
	private int days;
	
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public Trip getTrip() {
		return trip;
	}
	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public String getBookingNr() {
		return bookingNr;
	}
	public void setBookingNr(String bookingNr) {
		this.bookingNr = bookingNr;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Currency getPrice() {
		return price;
	}
	public void setPrice(Currency price) {
		this.price = price;
	}
	public Currency getBtw() {
		return btw;
	}
	public void setBtw(Currency btw) {
		this.btw = btw;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public Discount getDiscount() {
		return discount;
	}
	public void setDiscount(Discount discount) {
		this.discount = discount;
	}
}
