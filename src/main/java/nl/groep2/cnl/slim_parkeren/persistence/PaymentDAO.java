package nl.groep2.cnl.slim_parkeren.persistence;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.mongodb.morphia.Datastore;

import nl.groep2.cnl.slim_parkeren.model.Payment;

public class PaymentDAO extends BaseDAO<Payment> {

	@Inject
	public PaymentDAO(Datastore ds) {
		super(Payment.class, ds);
		// TODO Auto-generated constructor stub
	}
	
	public Response addPayment(Payment payment){
		if(save( payment ) != null)
			return Response.ok().build();
   	 else 
   		return Response.serverError().build();  
	}

}
