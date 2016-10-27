package nl.groep2.cnl.slim_parkeren.resource;


import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nl.groep2.cnl.slim_parkeren.model.Trip;
import nl.groep2.cnl.slim_parkeren.service.TripService;

@Path( "/trips" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class TripResource extends BaseResource{

	
    private final TripService tripService;

    @Inject
    public TripResource(TripService tripService){
        this.tripService = tripService;
    }
    
    @RolesAllowed("ADMIN")
    @POST
    @Path("/create")
    public Response create(@Valid Trip trip){
		return tripService.Create(trip);
    }

}
