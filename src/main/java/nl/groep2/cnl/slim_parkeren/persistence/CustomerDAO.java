package nl.groep2.cnl.slim_parkeren.persistence;

import java.util.List;

import javax.ws.rs.core.Response;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import com.google.inject.Inject;

import nl.groep2.cnl.slim_parkeren.model.Car;
import nl.groep2.cnl.slim_parkeren.model.Customer;

public class CustomerDAO extends BaseDAO<Customer> {

	private final CarDAO carDAO;
	@Inject
    public CustomerDAO(Datastore ds, CarDAO carDAO){
        super( Customer.class, ds );
        this.carDAO = carDAO;
    }
    
    public Response createCustomer(Customer customer){
    	if(save( customer ) != null)
    		return Response.status(Response.Status.CREATED).build();
    	return Response.serverError().build();  
    }
    
    public Customer getByEmail(String email){
    	Query<Customer> query = createQuery().field("email").equal(email);
        return findOne( query );
    }
    
    public Customer get(String id){
    	Customer customer = super.get(id);
    	List<Car> cars = carDAO.getByCustomerId(id);
    	customer.setCars(cars);
    	return customer;    	
    }
    
    public Response addAll(List<Customer> customers){
    	for(Customer customer : customers)
    		if(save( customer ) == null)
        		return Response.serverError().build();  
    	return Response.status(Response.Status.CREATED).build();
    }
    
    public Response updateCustomer(Customer customer){
    	Query<Customer> query = createQuery().field("id").equal(customer.getId());
    	UpdateOperations<Customer> ops = createUpdateOperations().set("phone", customer.getPhone());
    	if(update(query, ops) == null)
    		return Response.serverError().build();  
		return Response.status(Response.Status.OK).build();
    }
    
    public Response deleteCustomer(Customer customer){
    	if(delete(customer ) != null)
    		return Response.status(Response.Status.OK).build(); 
    	return Response.status(Response.Status.EXPECTATION_FAILED).build();  
    }
}
