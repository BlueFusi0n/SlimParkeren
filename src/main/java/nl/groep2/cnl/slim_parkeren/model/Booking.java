package nl.groep2.cnl.slim_parkeren.model;

import java.util.Currency;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(value = "Bookings", noClassnameStored = true)
public class Booking extends EntityModel  {
	
	@Embedded
	@NotNull
	@JsonProperty("Trip")
	private Trip trip;
	
	@Embedded
	@NotNull
	@JsonProperty("Customer")
	private Customer customer;
	
	@Embedded
	@NotNull
	@JsonProperty("Car")
	private Car car;
	
	@Embedded
	private Discount discount;
	
	@Embedded
	private Payment payment;
	
	@JsonProperty("Comments")
	private String comments;
	
	@NotEmpty
	@JsonProperty("PaymentId")
	private String paymentId;
	
	@JsonProperty("DiscountId")
	private String discountId;
	
	private String customerId, carId, tripId;
	private double price, btw;
	private int days;
	
	
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
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getDiscountId() {
		return discountId;
	}
	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getBtw() {
		return btw;
	}
	public void setBtw(double btw) {
		this.btw = btw;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public String getTripId() {
		return tripId;
	}
	public void setTripId(String tripId) {
		this.tripId = tripId;
	}
	public Discount getDiscount() {
		return discount;
	}
	public void setDiscount(Discount discount) {
		this.discount = discount;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}
