package nl.groep2.cnl.slim_parkeren.service;

import java.util.Optional;

import javax.inject.Inject;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import nl.groep2.cnl.slim_parkeren.model.User;
import nl.groep2.cnl.slim_parkeren.persistence.UserDAO;

public class AuthenticationService extends BaseService implements Authenticator< BasicCredentials, User >
{
    private final UserDAO userDAO;

    @Inject
    public AuthenticationService( UserDAO userDAO )
    {
        this.userDAO = userDAO;
    }

    @Override
    public Optional < User > authenticate( BasicCredentials credentials )
            throws AuthenticationException
    {
        User user = userDAO.getByName( credentials.getUsername() );
        
        if ( (user != null) && checkPassword( user, credentials.getPassword() ) )
            return Optional.of( user );
        else
            return Optional.empty();
    }

    private boolean checkPassword( User user, String password )
    {
        return user.getPassword().equals( password );
    }

}
