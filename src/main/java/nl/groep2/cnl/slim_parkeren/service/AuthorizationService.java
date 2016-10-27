package nl.groep2.cnl.slim_parkeren.service;

import io.dropwizard.auth.Authorizer;
import nl.groep2.cnl.slim_parkeren.model.User;

public class AuthorizationService extends BaseService implements Authorizer< User >
{
    @Override
    public boolean authorize( User authenticator, String role )
    {
        return authenticator.hasRole( role );
    }
}
