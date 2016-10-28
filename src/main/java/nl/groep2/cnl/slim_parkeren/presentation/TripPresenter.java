package nl.groep2.cnl.slim_parkeren.presentation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import nl.groep2.cnl.slim_parkeren.model.Trip;
import nl.groep2.cnl.slim_parkeren.presentation.model.TripView;

public class TripPresenter extends BasePresenter{

	
    public TripView present( Trip trip)
    {
    	TripView view = new TripView();

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");     
        
        view.id = trip.getId().toString();
        view.flightNr1 = trip.getFlightNr1();
        view.flightNr2 = trip.getFlightNr2();
        view.arrivalDate = df.format(trip.getArrivalDate());
        view.departureDate = df.format(trip.getDepartureDate());
                
        return view;
    }

    public List<TripView> present( List<Trip> trips)
    {
        List<TripView> views = new ArrayList<TripView>();
        
        for( Trip trip : trips )
            views.add( present( trip ) );
        
        return views;
    }
	
}
