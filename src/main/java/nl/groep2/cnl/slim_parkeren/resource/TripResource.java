package nl.groep2.cnl.slim_parkeren.resource;


import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.auth.Auth;
import nl.groep2.cnl.slim_parkeren.model.Customer;
import nl.groep2.cnl.slim_parkeren.model.Trip;
import nl.groep2.cnl.slim_parkeren.model.User;
import nl.groep2.cnl.slim_parkeren.presentation.TripPresenter;
import nl.groep2.cnl.slim_parkeren.presentation.model.TripView;
import nl.groep2.cnl.slim_parkeren.presentation.model.UserView;
import nl.groep2.cnl.slim_parkeren.service.TripService;

@Path( "/trips" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class TripResource extends BaseResource{

	
    private final TripService tripService;
    private final TripPresenter tripPresenter;

    @Inject
    public TripResource(TripService tripService, TripPresenter tripPresenter){
        this.tripService = tripService;
        this.tripPresenter = tripPresenter;
    }
    
    @RolesAllowed("ADMIN")
    @GET
    public List<TripView> getAll(){
        List<Trip> trips = tripService.getAll();        
        if (trips == null) 
            return null;
        return tripPresenter.present(trips);
    }
    
    @RolesAllowed("ADMIN")
    @POST
    public Response create(@Valid Trip trip){
		return tripService.create(trip);
    }
    
    
    @RolesAllowed("ADMIN")
    @GET
    @Path("/{tripID}")
    public TripView get(@PathParam("tripID") String tripID){
    	Trip trip = tripService.get(tripID);
        if (trip == null) 
            return null;      
        return tripPresenter.present(trip);
    }
    
    @RolesAllowed("ADMIN")
    @PUT
    @Path("/{tripID}")
    public Response update(@PathParam("tripID") String tripID, @Valid Trip trip){
    	Trip t = tripService.get(tripID);
    	if(t == null)
    		return Response.noContent().build();
    	return tripService.update(t, trip);
    }
    
    @RolesAllowed("ADMIN")  
    @DELETE
    @Path("/{tripID}")
    public Response delete(@PathParam("tripID") String tripID){
    	Trip trip = tripService.get(tripID);
    	if(trip == null)
    		return Response.noContent().build();
    	return tripService.delete(trip);
    }
    

}
