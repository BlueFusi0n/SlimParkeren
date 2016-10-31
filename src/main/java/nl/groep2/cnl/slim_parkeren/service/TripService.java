package nl.groep2.cnl.slim_parkeren.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;

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
    
    
    public Response deleteAll(){	    	
	   return tripDAO.deleteTrips(); 	
    }
    
    public Response deleteAll(String date){	  
    	
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
        
        if (date.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})"))
            try {
            	Date parsedDate = df.parse(date);
    			return tripDAO.deleteTrips(parsedDate); 	
    		} catch (ParseException e) {
    			return Response.notAcceptable(null).build();
    		}
        else
        	return Response.notAcceptable(null).build();
       
    }
        
    public Trip get(String id){
    	return tripDAO.get(id);
    }
    
    public Response update(Trip t, Trip trip){
    	
   	   	Date lastUpdateDay = java.sql.Date.valueOf(LocalDate.now().plusWeeks(1));

		if(checkDates(trip) && t.getDepartureDate().after(lastUpdateDay))
			return tripDAO.updateTrip(t, trip);  	
		
    	return Response.notAcceptable(null).build();
    }
    
    public Response delete(Trip trip){
    	
   	   	Date lastUpdateDay = java.sql.Date.valueOf(LocalDate.now().plusWeeks(1));
    	
		if(trip.getDepartureDate().after(lastUpdateDay))
	    	return tripDAO.deleteObject(trip); 	

    	return Response.notAcceptable(null).build();
    }
    
    
    public ObjectId create(Trip trip){
    	
    	if(checkDates(trip))
    		return tripDAO.createTrip(trip);  	

    	return null;

    }

   
    public boolean checkDates(Trip trip)
    {
    	return trip.getDepartureDate().after(new Date()) && trip.getArrivalDate().after(trip.getDepartureDate());
    }
    
}
