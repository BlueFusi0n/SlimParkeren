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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.groep2.cnl.slim_parkeren.model.Car;
import nl.groep2.cnl.slim_parkeren.presentation.CarPresenter;
import nl.groep2.cnl.slim_parkeren.presentation.model.CarView;
import nl.groep2.cnl.slim_parkeren.service.CarService;

@Path( "/cars" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class CarResource extends BaseResource {
	
    private final CarService carService;
    private final CarPresenter carPresenter;

    @Inject
    public CarResource(CarService carService, CarPresenter carPresenter){
    	this.carService = carService;
    	this.carPresenter = carPresenter;
    }
      
    @RolesAllowed("ADMIN")
    @GET
    public List<CarView> getAll(@QueryParam("color") String color){
    	List<Car> cars;
        if(color != null)
        	cars = carService.getAllByColor(color);
        else 
        	cars = carService.getAll();
        return carPresenter.present(cars);
    }
    
    
    @RolesAllowed("ADMIN")
    @POST
    @Path("/addList")
    public Response addAll(@Valid List<Car> cars){
    	return carService.add(cars);
    }
        
    @RolesAllowed("ADMIN")  
    @DELETE
    @Path("/{license}")
    public Response delete(@PathParam("license") String license){
    	Car c = carService.get(license);
    	if(c == null) return Response.noContent().build();
    	return carService.delete(c);
    }
}