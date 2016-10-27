package nl.groep2.cnl.slim_parkeren.model;

import java.util.Currency;

import org.mongodb.morphia.annotations.Embedded;

public class Booking extends EntityModel  {
	
	@Embedded
	private Car car;
	@Embedded
	private Trip trip;
	@Embedded
	private Customer customer;
	@Embedded
	private Payment payment;
	private String bookingNr, comments;
	private Currency price, btw; 
	private int days;
	@Embedded
	private Discount discount;
	
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
