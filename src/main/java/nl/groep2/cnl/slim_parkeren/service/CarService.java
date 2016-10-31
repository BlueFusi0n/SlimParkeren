package nl.groep2.cnl.slim_parkeren.service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;

import nl.groep2.cnl.slim_parkeren.model.Car;
import nl.groep2.cnl.slim_parkeren.persistence.CarDAO;

public class CarService extends BaseService {

	private final CarDAO carDAO;
    
    @Inject
    public CarService(CarDAO carDAO){
        this.carDAO = carDAO;
    }
    
    public Car get(String id){
    	return carDAO.get(id);
    }
    
    public Car getByLicense(String license){
    	return carDAO.getByLicense(license);
    }
    
    public List<Car> getAllByCustomerId(String id){
    	return carDAO.getByCustomerId(id);
    }

    public List<Car> getAll(){
        return carDAO.getAll();
    }
    
    public List<Car> getAllByColor(String color){
    	return carDAO.getAllByColor(color);
    }
    
    public ObjectId add(String id, Car car){
    	return carDAO.addCar(id, car);
    }
    
    public Response add(List<Car> cars){
    	return carDAO.addCars(cars);
    }
    
    public Response delete(Car car){
    	return carDAO.deleteCar(car);
    }
}
