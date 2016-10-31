package nl.groep2.cnl.slim_parkeren.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import static java.lang.Math.toIntExact;

import org.bson.types.ObjectId;

import nl.groep2.cnl.slim_parkeren.model.Booking;
import nl.groep2.cnl.slim_parkeren.persistence.BookingDAO;
import nl.groep2.cnl.slim_parkeren.persistence.DiscountDAO;

public class BookingService extends BaseService{
	
	private final BookingDAO bookingDAO;
	private final DiscountDAO discountDAO;
	
	@Inject
	public BookingService(BookingDAO bookingDAO, DiscountDAO discountDAO){
		this.bookingDAO = bookingDAO;
		this.discountDAO = discountDAO;
	}

	public ObjectId add(Booking booking){
		Date arrDate = booking.getTrip().getDepartureDate();
		Date depDate = booking.getTrip().getArrivalDate();
		
		int days = Math.abs(toIntExact((arrDate.getTime() - depDate.getTime()) / (1000 * 60 * 60 * 24))); 
		booking.setDays(days); 
		double price = (40 + days * 5);		
		if(booking.getDiscountId() != null)
			price -= price * (discountDAO.get(booking.getDiscountId()).getDeal() / 100.0);
		
		booking.setPrice(price);		
		booking.setBtw(price*.21);
		
		booking.setTrip(null);
		
		return bookingDAO.add(booking);
	}
	
	public List<Booking> getAll(){
		return bookingDAO.getAll();
	}
}
