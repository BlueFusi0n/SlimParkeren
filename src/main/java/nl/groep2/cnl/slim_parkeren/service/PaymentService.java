package nl.groep2.cnl.slim_parkeren.service;

import java.util.List;

import javax.ws.rs.core.Response;

import com.google.inject.Inject;

import nl.groep2.cnl.slim_parkeren.model.Payment;
import nl.groep2.cnl.slim_parkeren.persistence.PaymentDAO;

public class PaymentService {
	
	private final PaymentDAO paymentDAO;
	
	@Inject
	public PaymentService(PaymentDAO paymentDAO)
	{
		this.paymentDAO = paymentDAO;
	}
	
	 public List<Payment> getAll(){
	        return paymentDAO.getAll();
	    }
	 
	 public Response add(Payment payment){
			
		 if (payment.getType() == null)
			return Response.notAcceptable(null).build();	 	 
			
		return paymentDAO.addPayment(payment);
	    }

}
