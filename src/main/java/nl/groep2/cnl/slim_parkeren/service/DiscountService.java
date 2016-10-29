package nl.groep2.cnl.slim_parkeren.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import nl.groep2.cnl.slim_parkeren.model.Discount;
import nl.groep2.cnl.slim_parkeren.model.Trip;
import nl.groep2.cnl.slim_parkeren.persistence.DiscountDAO;


public class DiscountService extends BaseService {
	
	private final DiscountDAO discountDAO;
	private boolean exists = false;
	
	@Inject
	public DiscountService (DiscountDAO discountDAO)
	{
		this.discountDAO = discountDAO;
	}
	
	 public List<Discount> getAll(){
	        return discountDAO.getAll();
	    }
	 
	 public Response add(Discount discount){
	
		 if(discountDAO.doesCodeExists(discount.getCode()))
			return Response.notAcceptable(null).build();	 	 
			
		return discountDAO.addDiscount(discount);	    	
	    }
	 
	 public Discount get(String id){
	    	return discountDAO.get(id);
	    }
	 
	    public Response update(Discount discount1, Discount discount2){
	    	
	   	   	Date CurrentTime = java.sql.Date.valueOf(LocalDate.now());

			if(discount2.getExpiryDate().after(CurrentTime))
				return discountDAO.updateTrip(discount1, discount2);  	
			
	    	return Response.notAcceptable(null).build();
	    }
	    
	    public Response delete(Discount discount){
	    	
	    	Date CurrentTime = java.sql.Date.valueOf(LocalDate.now());
	    	
	    	if (discount.getExpiryDate().before(CurrentTime))
	    		return discountDAO.deleteObject(discount);
	    	
	    	return Response.notAcceptable(null).build();
	    	
	    	
	    	
	    	
	    }
	    

}
