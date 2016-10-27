package nl.groep2.cnl.slim_parkeren.persistence;

import java.util.List;

import javax.ws.rs.core.Response;

import org.mongodb.morphia.Datastore;

import com.google.inject.Inject;

import nl.groep2.cnl.slim_parkeren.model.Car;

public class CarDAO extends BaseDAO<Car> {

	@Inject
    public CarDAO(Datastore ds){
        super(Car.class, ds);
    }
    
    public Response addCar(Car car){
    	if(save( car ) != null)
    		return Response.status(Response.Status.CREATED).build();   	 
    	return Response.serverError().build();  
    }
    
    public Response addCars(List<Car> cars){
    	for(Car car : cars)
    		if(save( car ) == null)
        		return Response.serverError().build();  
    	return Response.status(Response.Status.CREATED).build();
    }
        
    public Response deleteCar(Car car){
    	if(delete(car ) != null)
    		return Response.status(Response.Status.OK).build(); 
    	return Response.status(Response.Status.EXPECTATION_FAILED).build();  
    }
}
