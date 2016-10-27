package nl.groep2.cnl.slim_parkeren.service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import nl.groep2.cnl.slim_parkeren.model.User;
import nl.groep2.cnl.slim_parkeren.persistence.UserDAO;


public class UserService extends BaseService
{
    private final UserDAO userDAO;
    
    @Inject
    public UserService( UserDAO userDAO ){
        this.userDAO = userDAO;
    }
    
    public User get( User authenticator, String id ){
        if (!authenticator.getId().toString().equals(id))
            return null;                
        return userDAO.get(id);
    }

    public List<User> getAll(){
        return userDAO.getAll();
    }
    
    public Response Create(User user){
    	return userDAO.createUser(user);
    }

}
