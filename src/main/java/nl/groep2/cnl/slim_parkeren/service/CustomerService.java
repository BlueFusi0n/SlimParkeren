package nl.groep2.cnl.slim_parkeren.service;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;

import nl.groep2.cnl.slim_parkeren.model.Customer;
import nl.groep2.cnl.slim_parkeren.persistence.CustomerDAO;

public class CustomerService extends BaseService {

	private final CustomerDAO customerDAO;
    
    @Inject
    public CustomerService(CustomerDAO customerDAO){
        this.customerDAO = customerDAO;
    }
    
    public Customer get(String id){
    	return customerDAO.get(id);
    }
    
    public Customer getByEmail(String email ){
        return customerDAO.getByEmail( email );
    }

    public List<Customer> getAll(){
        return customerDAO.getAll();
    }
    
    public ObjectId add(Customer customer){
    	return customerDAO.createCustomer(customer);
    }
    
    public Response add(List<Customer> customers){
    	return customerDAO.addAll(customers);
    }
    
    public Response update(Customer customer){
    	return customerDAO.updateCustomer(customer);
    }
    
    public Response delete(Customer customer){
    	return customerDAO.deleteCustomer(customer);
    }
}
