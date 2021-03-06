package nl.groep2.cnl.slim_parkeren.persistence;

import java.util.List;

import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.google.inject.Inject;

import nl.groep2.cnl.slim_parkeren.model.Car;

public class CarDAO extends BaseDAO<Car> {

	@Inject
    public CarDAO(Datastore ds){
        super(Car.class, ds);
    }
	
	public List<Car> getAllByColor(String color){
		Query<Car> query = createQuery().field("color").equalIgnoreCase(color);
		return find(query).asList();
	}
	
	public Car getByLicense(String license){
		Query<Car> query = createQuery().field("licensePlate").equalIgnoreCase(license);
    	Car car = find(query).get();
    	return car;
	}
    
    public ObjectId addCar(String id, Car car){
    	Query<Car> query = createQuery().field("licensePlate").equalIgnoreCase(car.getLicensePlate());
    	if(find(query).get() == null){
	    	car.setCustomerId(id);
	    	return (ObjectId) save(car).getId();
    	}
		return null;
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
    
    public List<Car> getByCustomerId(String id){
    	Query<Car> query = createQuery().field("customerId").equal(id);
    	return find(query).asList();
    }
}
