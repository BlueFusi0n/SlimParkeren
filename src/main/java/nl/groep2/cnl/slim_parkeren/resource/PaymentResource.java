package nl.groep2.cnl.slim_parkeren.resource;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;

import nl.groep2.cnl.slim_parkeren.model.Payment;
import nl.groep2.cnl.slim_parkeren.presentation.PaymentPresenter;
import nl.groep2.cnl.slim_parkeren.presentation.model.PaymentView;
import nl.groep2.cnl.slim_parkeren.service.PaymentService;

@Path("/payments")
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )

public class PaymentResource extends BaseResource {
	
	public final PaymentService paymentService;
	public final PaymentPresenter paymentPresenter;
	
	@Inject
	public PaymentResource(PaymentService paymentService, PaymentPresenter paymentPresenter){
		this.paymentService = paymentService;
		this.paymentPresenter = paymentPresenter;	
	}
	
	
	  @RolesAllowed("ADMIN")
	    @GET
	    public List <PaymentView> getAll(@QueryParam("type") String type){
	    	
	    	List<Payment> listOfPayments = paymentService.getAll();
	    	
	    	if (listOfPayments == null)
	    		return null;
	        return paymentPresenter.present(listOfPayments);
	    	
	    }
	    
	    @RolesAllowed("ADMIN")
	    @POST
	    public Response create(@Valid Payment payment){
			return paymentService.add(payment);
	    }

}
