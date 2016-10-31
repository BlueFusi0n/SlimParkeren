package nl.groep2.cnl.slim_parkeren.persistence;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import nl.groep2.cnl.slim_parkeren.model.Discount;

public class DiscountDAO extends BaseDAO<Discount>{

	@Inject
	public DiscountDAO(Datastore ds) {
		super(Discount.class, ds);
		// TODO Auto-generated constructor stub
	}
	
    public Response addDiscount(Discount discount){
    	if(save( discount ) != null)
    		return Response.status(Response.Status.CREATED).build();   	 
    	return Response.serverError().build(); 
    }
    
    
    public boolean doesCodeExists(String code)
    {
    	Query<Discount> query = createQuery().field("code").equal(code);
    	if(find(query).get() != null)
    		return true;
    	
		return false;
    }
    
    public List<Discount> getSome(int amount)
    {
    	Query<Discount> query = createQuery().field("amount").greaterThanOrEq(amount);
    	if(find(query).get() != null)
    		return find(query).asList(); 
    	return null;
    }
    
    public Response updateTrip(Discount discount1, Discount discount2){
    	discount2.setId(discount1.getId());
    	if(save(discount2) != null)
    		return Response.ok().build();
    	 else 
    		return Response.serverError().build();  
    	    	
    }
	
	
	

}
