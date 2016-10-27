package nl.groep2.cnl.slim_parkeren;

import javax.inject.Singleton;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class SlimParkerenModule extends AbstractModule
{
    @Override
    protected void configure(){
    }
    
    @Provides @Singleton
    Datastore providesDatastore(){
        MongoClient mongo = new MongoClient(new ServerAddress("localhost", 27017));

        Morphia morphia = new Morphia();

        return morphia.createDatastore(mongo, "SlimParkeren");      
    }
}