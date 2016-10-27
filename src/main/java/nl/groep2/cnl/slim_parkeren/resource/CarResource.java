package nl.groep2.cnl.slim_parkeren.resource;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.auth.Auth;
import nl.groep2.cnl.slim_parkeren.model.Car;
import nl.groep2.cnl.slim_parkeren.model.User;
import nl.groep2.cnl.slim_parkeren.service.CarService;

@Path( "/cars" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class CarResource extends BaseResource {
	
    private final CarService carService;

    @Inject
    public CarResource(CarService carService){
    	this.carService = carService;
    }
    
    @RolesAllowed("ADMIN")
    @GET
    public List <Car> getAll( @Auth User authenticator ){
        List<Car> cars = carService.getAll();    
        if (cars == null) 
            return null;
        return cars;
    }
    
    @GET
    @Path( "/{license}" )
    public Car get( @PathParam( "license" ) String license){
        Car car = carService.get(license);
        if (car == null) 
            return null;
        return car;    
    }
    
    @RolesAllowed("ADMIN")
    @POST
    @Path("/add")
    public Response create(@Valid Car car){
		return carService.Add(car);
    }
    
    @RolesAllowed("ADMIN")
    @POST
    @Path("/addList")
    public Response createAll(@Valid List<Car> cars){
    	return carService.Add(cars);
    }
        
    @RolesAllowed("ADMIN")  
    @DELETE
    @Path("/{license}")
    public Response delete(@PathParam("license") String license){
    	Car c = carService.get(license);
    	if(c == null)
    		return Response.noContent().build();
    	return carService.delete(c);
    }
}