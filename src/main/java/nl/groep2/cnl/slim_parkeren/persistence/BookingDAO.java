package nl.groep2.cnl.slim_parkeren.persistence;

import java.util.List;

import javax.inject.Inject;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;

import nl.groep2.cnl.slim_parkeren.model.Booking;

public class BookingDAO extends BaseDAO<Booking> {
	
	private final CarDAO carDAO;
	private final CustomerDAO customerDAO;
	private final TripDAO tripDAO;
	private final PaymentDAO paymentDAO;
	private final DiscountDAO discountDAO;

	@Inject
	public BookingDAO(Datastore ds, CarDAO carDAO, CustomerDAO customerDAO, TripDAO tripDAO, PaymentDAO paymentDAO, DiscountDAO discountDAO) {
		super(Booking.class, ds);
		this.carDAO = carDAO;
		this.customerDAO = customerDAO;
		this.tripDAO = tripDAO;
		this.paymentDAO = paymentDAO;
		this.discountDAO = discountDAO;
	}
	
	public ObjectId add(Booking booking){
		ObjectId id = (ObjectId) save(booking).getId();
		return id;
	}
	
	public List<Booking> getAll(){
		List<Booking> bookings = super.getAll();
		for(Booking booking : bookings){
			booking.setCar(carDAO.get(booking.getCarId()));
			booking.setCustomer(customerDAO.get(booking.getCustomerId()));
			booking.setTrip(tripDAO.get(booking.getTripId()));
			booking.setPayment(paymentDAO.get(booking.getPaymentId()));
			booking.setDiscount(discountDAO.get(booking.getDiscountId()));
		}
		return bookings;
	}

}
