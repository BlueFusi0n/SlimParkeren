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

import org.bson.types.ObjectId;

import io.dropwizard.auth.Auth;
import nl.groep2.cnl.slim_parkeren.model.Car;
import nl.groep2.cnl.slim_parkeren.model.Customer;
import nl.groep2.cnl.slim_parkeren.model.User;
import nl.groep2.cnl.slim_parkeren.presentation.CarPresenter;
import nl.groep2.cnl.slim_parkeren.presentation.CustomerPresenter;
import nl.groep2.cnl.slim_parkeren.presentation.ResponsePresenter;
import nl.groep2.cnl.slim_parkeren.presentation.model.CarView;
import nl.groep2.cnl.slim_parkeren.presentation.model.CustomerView;
import nl.groep2.cnl.slim_parkeren.presentation.model.ResponseView;
import nl.groep2.cnl.slim_parkeren.service.CarService;
import nl.groep2.cnl.slim_parkeren.service.CustomerService;

@Path( "/customers" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class CustomerResource extends BaseResource {
	
    private final CustomerService customerService;
    private final CarService carService;
    private final CustomerPresenter customerPresenter;
    private final CarPresenter carPresenter;
    private final ResponsePresenter responsePresenter;

    @Inject
    public CustomerResource(CustomerService customerService, CustomerPresenter customerPresenter, CarService carService, CarPresenter carPresenter, ResponsePresenter responsePresenter){
    	this.customerService = customerService;
    	this.customerPresenter = customerPresenter;
    	this.carService = carService;
    	this.carPresenter = carPresenter;
    	this.responsePresenter = responsePresenter;
    }
    
    @RolesAllowed("ADMIN")
    @GET
    public List < CustomerView > getAll( @Auth User authenticator ){
        List<Customer> customers = customerService.getAll();    
        if (customers == null) 
            return null;
        return customerPresenter.present(customers);
    }
    
	@RolesAllowed("ADMIN")
    @GET
    @Path( "/{email}" )
    public CustomerView get( @PathParam( "email" ) String email){
        Customer customer = customerService.get(email);
        if (customer == null) 
            return null;
        return customerPresenter.present(customer);
    }
    
    @RolesAllowed("ADMIN")
    @POST
    public ResponseView add(@Valid Customer customer){
    	ObjectId id = customerService.add(customer);
    	return id != null ? responsePresenter.present(id, "Success") : responsePresenter.present(null, "Failure");
    }
    
	@RolesAllowed("ADMIN")
    @POST
    @Path("/{id}/cars")
    public ResponseView addCar(@PathParam("id") String id, @Valid Car car){
    	Customer customer = customerService.get(id);
    	if(customer == null)
    		return responsePresenter.present(null, "Failure");
    	ObjectId carId = carService.add(id, car);
    	return carId != null ? responsePresenter.present(carId, "Success") : responsePresenter.present(null, "Failure");
    }
    
	@RolesAllowed("ADMIN")
    @GET
    @Path("/{id}/cars")
    public List<CarView> getCar(@PathParam("id") String id){
    	Customer customer = customerService.get(id);
    	if(customer == null) return null;
    	return carPresenter.present(carService.getAllByCustomerId(id));
    }
    
    @RolesAllowed("ADMIN")
    @POST
    @Path("/addList")
    public Response addAll(@Valid List<Customer> customers){
    	return customerService.add(customers);
    }
    
    @RolesAllowed("ADMIN")
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, @Valid Customer customer){
    	Customer c = customerService.get(id);
    	if(c == null)
    		return Response.noContent().build();
    	return customerService.update(customer);
    }
    
    @RolesAllowed("ADMIN")  
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id){
    	Customer c = customerService.get(id);
    	if(c == null)
    		return Response.noContent().build();
    	return customerService.delete(c);
    }
}
