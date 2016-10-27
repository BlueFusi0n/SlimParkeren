package nl.groep2.cnl.slim_parkeren.persistence;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.mongodb.morphia.Datastore;

import nl.groep2.cnl.slim_parkeren.model.Trip;

public class TripDAO extends BaseDAO<Trip> {

	@Inject
	public TripDAO(Datastore ds) {
		super(Trip.class, ds);
		// TODO Auto-generated constructor stub
	}

	
    public Response createTrip(Trip trip){
    	if(save( trip ) != null)
    		return Response.ok().build();
    	 else 
    		return Response.serverError().build();  
    }
}
