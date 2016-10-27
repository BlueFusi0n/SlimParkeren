package nl.groep2.cnl.slim_parkeren.service;

import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import nl.groep2.cnl.slim_parkeren.model.Trip;
import nl.groep2.cnl.slim_parkeren.persistence.TripDAO;

public class TripService extends BaseService{
    private final TripDAO tripDAO;
    
    @Inject
    public TripService( TripDAO tripDAO ){
        this.tripDAO = tripDAO;
    }
    
    public Response Create(Trip trip){
    	
    	if(trip.getDepartureDate().after(new Date()) && trip.getArrivalDate().after(trip.getDepartureDate()))
    	{
        	return tripDAO.createTrip(trip);  	
    	}
   
    	return Response.notAcceptable(null).build();

    }

   
}
