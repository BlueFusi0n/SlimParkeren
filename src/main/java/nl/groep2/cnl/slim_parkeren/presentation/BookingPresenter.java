package nl.groep2.cnl.slim_parkeren.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import nl.groep2.cnl.slim_parkeren.model.Booking;
import nl.groep2.cnl.slim_parkeren.presentation.model.BookingView;

public class BookingPresenter extends BasePresenter {
	
	private final CarPresenter carPresenter;
	private final CustomerPresenter customerPresenter;
	private final TripPresenter tripPresenter;
	
	@Inject
	public BookingPresenter(CarPresenter carPresenter, CustomerPresenter customerPresenter, TripPresenter tripPresenter){
		this.carPresenter = carPresenter;
		this.customerPresenter = customerPresenter;
		this.tripPresenter = tripPresenter;
	}
	
	public BookingView present(Booking booking){
		BookingView view = new BookingView();
		view.car = carPresenter.present(booking.getCar());
		view.customer = customerPresenter.present(booking.getCustomer());
		view.trip = tripPresenter.present(booking.getTrip());
		view.payment = booking.getPayment().getType();
		view.discountCode = booking.getDiscount().getCode();
		view.price = booking.getPrice();
		view.btw = booking.getBtw();
		view.days = booking.getDays();
		view.comments = booking.getComments();
		return view;
	}
	
	public List<BookingView> present(List<Booking> bookings){
		List<BookingView> views = new ArrayList<BookingView>();
		for(Booking booking : bookings){
			views.add(present(booking));
		}
		return views;
	}

}
