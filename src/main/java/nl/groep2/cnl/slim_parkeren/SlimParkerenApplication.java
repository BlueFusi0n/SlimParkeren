package nl.groep2.cnl.slim_parkeren;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import com.hubspot.dropwizard.guice.GuiceBundle;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import nl.groep2.cnl.slim_parkeren.model.User;
import nl.groep2.cnl.slim_parkeren.service.AuthenticationService;
import nl.groep2.cnl.slim_parkeren.service.AuthorizationService;

public class SlimParkerenApplication extends Application < SlimParkerenConfiguration >
{
    private GuiceBundle < SlimParkerenConfiguration > guiceBundle ;
    
    public static void main( final String[] args ) throws Exception
    {
        new SlimParkerenApplication()
        .run( new String[] { "server" } );
    }

    @Override
    public void initialize(
            final Bootstrap < SlimParkerenConfiguration > bootstrap )
    {
        configureGuice();
        bootstrap.addBundle( guiceBundle );
    }

    private void configureGuice()
    {
        guiceBundle = GuiceBundle.< SlimParkerenConfiguration > newBuilder()
                .addModule( new SlimParkerenModule() )
                .enableAutoConfig( getClass().getPackage().getName() )
                .setConfigClass( SlimParkerenConfiguration.class ).build();
    }

    @Override
    public void run( final SlimParkerenConfiguration configuration,
                     final Environment environment ) throws Exception
    {
        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<User>()
                    .setAuthenticator( 
                            guiceBundle.getInjector().getInstance( 
                                    AuthenticationService.class ) )
                    .setAuthorizer( 
                            guiceBundle.getInjector().getInstance( 
                                    AuthorizationService.class ) )
                    .setRealm( "SUPER SECRET STUFF" )
                    .buildAuthFilter()));
        
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));     
    }

}
