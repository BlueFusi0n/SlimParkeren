package nl.groep2.cnl.slim_parkeren.service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    
    
    public List<Trip> getAll(){
        return tripDAO.getAll();
    }
    
    
    public Trip get(String id){
    	return tripDAO.get(id);
    }
    
    public Response update(Trip t, Trip trip){
    	
   	   	Date lastUpdateDay = java.sql.Date.valueOf(LocalDate.now().minusWeeks(1));

    	
		if(checkDates(trip) && t.getDepartureDate().after(lastUpdateDay))
			return tripDAO.updateTrip(t, trip);  	
		
    	return Response.notAcceptable(null).build();
    }
    
    public Response delete(Trip trip){
    	return tripDAO.deleteObject(trip);
    }
    
    
    public Response create(Trip trip){
    	
    	if(checkDates(trip))
    		return tripDAO.createTrip(trip);  	

    	return Response.notAcceptable(null).build();

    }

   
    public boolean checkDates(Trip trip)
    {
    	return trip.getDepartureDate().after(new Date()) && trip.getArrivalDate().after(trip.getDepartureDate());
    }
    
}
