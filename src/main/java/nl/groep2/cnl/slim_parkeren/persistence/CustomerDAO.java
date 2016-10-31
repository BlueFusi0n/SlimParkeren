package nl.groep2.cnl.slim_parkeren.persistence;

import java.util.List;

import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;
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
    
    public ObjectId createCustomer(Customer customer){
    	return (ObjectId) save( customer ).getId();
    	
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
    
    public List<Customer> getAll(){
    	List<Customer> customers = super.getAll();
    	for(Customer customer : customers){
	    	List<Car> cars = carDAO.getByCustomerId(customer.getId().toString());
	    	customer.setCars(cars);
    	}
    	return customers;    
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
