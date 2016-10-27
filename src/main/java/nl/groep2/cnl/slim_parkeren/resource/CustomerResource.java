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
import nl.groep2.cnl.slim_parkeren.model.Car;
import nl.groep2.cnl.slim_parkeren.model.Customer;
import nl.groep2.cnl.slim_parkeren.model.User;
import nl.groep2.cnl.slim_parkeren.presentation.CustomerPresenter;
import nl.groep2.cnl.slim_parkeren.presentation.model.CustomerView;
import nl.groep2.cnl.slim_parkeren.service.CustomerService;

@Path( "/customers" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class CustomerResource extends BaseResource {
	
    private final CustomerService customerService;
    private final CustomerPresenter customerPresenter;

    @Inject
    public CustomerResource(CustomerService customerService, CustomerPresenter customerPresenter){
    	this.customerService = customerService;
    	this.customerPresenter = customerPresenter;
    }
    
    @RolesAllowed("ADMIN")
    @GET
    public List < CustomerView > getAll( @Auth User authenticator ){
        List<Customer> customers = customerService.getAll();    
        if (customers == null) 
            return null;
        return customerPresenter.present(customers);
    }
    
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
    @Path("/add")
    public Response create(@Valid Customer customer){
		return customerService.Create(customer);
    }
    
    @RolesAllowed("ADMIN")
    @POST
    @Path("/addList")
    public Response createAll(@Valid List<Customer> customers){
    	return customerService.Create(customers);
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
    
    @RolesAllowed("ADMIN")
    @PUT
    @Path("/{id}/addCar")
    public Response addCar(@PathParam("id") String id, @Valid Car car){
    	Customer customer = customerService.get(id);
    	if(customer == null)
    		return Response.noContent().build();
    	car.setId(customer.getId());
    	return customerService.addCar(car);
    }
}
