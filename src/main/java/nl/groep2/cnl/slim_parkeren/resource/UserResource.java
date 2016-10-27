package nl.groep2.cnl.slim_parkeren.resource;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.auth.Auth;
import nl.groep2.cnl.slim_parkeren.model.User;
import nl.groep2.cnl.slim_parkeren.presentation.UserPresenter;
import nl.groep2.cnl.slim_parkeren.presentation.model.UserView;
import nl.groep2.cnl.slim_parkeren.service.UserService;


@Path( "/users" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class UserResource extends BaseResource{
    private final UserService    userService;
    private final UserPresenter  userPresenter;

    @Inject
    public UserResource(UserService userService, UserPresenter userPresenter){
        this.userService = userService;
        this.userPresenter = userPresenter;
    }

    @RolesAllowed("ADMIN")
    @GET
    public List<UserView> getAll(){
        List<User> users = userService.getAll();        
        if (users == null) 
            return null;
        return userPresenter.present(users);
    }

    
    @GET
    @Path("/{userId}")
    public UserView get(@PathParam("userId") String userId, @Auth User authenticator){
        User user = userService.get(authenticator, userId);
        if (user == null) 
            return null;      
        return userPresenter.present(user);
    }
    
    
    @RolesAllowed("ADMIN")
    @POST
    public Response create(@Valid User user, @Auth User authenticator){
		return userService.Create(user);
    }
}
