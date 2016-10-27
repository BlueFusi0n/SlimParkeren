package nl.groep2.cnl.slim_parkeren.persistence;

import javax.ws.rs.core.Response;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.google.inject.Inject;

import nl.groep2.cnl.slim_parkeren.model.User;

public class UserDAO extends BaseDAO< User >
{
    @Inject
    public UserDAO( Datastore ds )
    {
        super( User.class, ds );
    }

    public User getByName( String username )
    {
        Query<User> query = createQuery().field( "name" ).equal( username );
        return findOne( query );
    }
    
    public Response createUser(User user){
    	if(save( user ) != null)
    		return Response.ok().build();
    	 else 
    		return Response.serverError().build();  
    }
}
