package nl.groep2.cnl.slim_parkeren.resource;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.types.ObjectId;

import nl.groep2.cnl.slim_parkeren.model.Booking;
import nl.groep2.cnl.slim_parkeren.model.Car;
import nl.groep2.cnl.slim_parkeren.model.Customer;
import nl.groep2.cnl.slim_parkeren.presentation.BookingPresenter;
import nl.groep2.cnl.slim_parkeren.presentation.ResponsePresenter;
import nl.groep2.cnl.slim_parkeren.presentation.model.BookingView;
import nl.groep2.cnl.slim_parkeren.presentation.model.ResponseView;
import nl.groep2.cnl.slim_parkeren.service.BookingService;
import nl.groep2.cnl.slim_parkeren.service.CarService;
import nl.groep2.cnl.slim_parkeren.service.CustomerService;
import nl.groep2.cnl.slim_parkeren.service.TripService;

@Path("/bookings")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookingResource extends BaseResource {
	
	private final BookingService bookingService;
	private final CustomerService customerService;
	private final CarService carService;
	private final TripService tripService;
	private final ResponsePresenter responsePresenter;
	private final BookingPresenter bookingPresenter;
	
	@Inject
	public BookingResource(BookingService bookingService, CustomerService customerService, 
			CarService carService, TripService tripService, ResponsePresenter responsePresenter,
			BookingPresenter bookingPresenter){
		this.customerService = customerService;
		this.bookingService = bookingService;
		this.carService = carService;
		this.tripService = tripService;
		this.responsePresenter = responsePresenter;
		this.bookingPresenter = bookingPresenter;
	}
	
	@POST
	public ResponseView addBooking(@Valid Booking booking){
		Customer customer = customerService.getByEmail(booking.getCustomer().getEmail());
		ObjectId id = null;
		if(customer != null)
			id = customer.getId();
		if(id == null){
			id = customerService.add(booking.getCustomer());
			booking.setCarId(carService.add(id.toString(), booking.getCar()).toString());
		}else{			
			Car car = carService.getByLicense(booking.getCar().getLicensePlate());		
			if(car == null)
				booking.setCarId(carService.add(id.toString(), booking.getCar()).toString());
			booking.setCarId(car.getId().toString());
		}
		booking.setTripId(tripService.create(booking.getTrip()).toString());

		booking.setCustomerId(id.toString());
		booking.setCar(null);
		booking.setCustomer(null);
		
		ObjectId bookingId = bookingService.add(booking);
    	return bookingId != null ? responsePresenter.present(bookingId, "Success") : responsePresenter.present(null, "Failure");
	}
	
	@GET
	public List<BookingView> get(){
		return bookingPresenter.present(bookingService.getAll());
	}
	
	

}
