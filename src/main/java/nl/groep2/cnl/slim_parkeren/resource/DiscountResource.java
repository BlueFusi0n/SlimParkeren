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
import nl.groep2.cnl.slim_parkeren.model.Discount;
import nl.groep2.cnl.slim_parkeren.model.User;
import nl.groep2.cnl.slim_parkeren.presentation.DiscountPresenter;
import nl.groep2.cnl.slim_parkeren.presentation.model.DiscountView;
import nl.groep2.cnl.slim_parkeren.service.DiscountService;

@Path("/discounts")
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )

public class DiscountResource extends BaseResource{
	
	private final DiscountService discountService;
	private final DiscountPresenter discountPresenter;
	
    @Inject
    public DiscountResource(DiscountService discountService, DiscountPresenter discountPresenter){
        this.discountService = discountService;
        this.discountPresenter = discountPresenter;
    }
    
    @RolesAllowed("ADMIN")
    @GET
    public List <DiscountView> getAll( @Auth User authenticator ){
        List<Discount> discounts = discountService.getAll();    
        if (discounts == null) 
            return null;
        return discountPresenter.present(discounts);
    }
    
    @RolesAllowed("ADMIN")
    @POST
    public Response create(@Valid Discount discount){
		return discountService.add(discount);
    }
    
    @RolesAllowed("ADMIN")
    @PUT
    @Path("/{discountId}")
    public Response update(@PathParam("discountId") String discountId, @Valid Discount discountNew){
    	Discount discountOld = discountService.get(discountId);
    	if (discountOld == null)
    		return Response.noContent().build();
    	
    	return discountService.update(discountOld, discountNew);
    }
    
    @RolesAllowed("ADMIN")
    @DELETE
    @Path("/{discountId}")
    public Response delete(@PathParam("discountId") String discountId){
    	Discount discount = discountService.get(discountId);
    	if (discount == null)
    		return Response.noContent().build();
    	return discountService.delete(discount);
    	//return discountService.update(discountOld, discountNew);
    	
    	
    }

}
