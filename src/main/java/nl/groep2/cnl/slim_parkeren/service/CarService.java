package nl.groep2.cnl.slim_parkeren.service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import nl.groep2.cnl.slim_parkeren.model.Car;
import nl.groep2.cnl.slim_parkeren.persistence.CarDAO;

public class CarService extends BaseService {

	private final CarDAO carDAO;
    
    @Inject
    public CarService(CarDAO carDAO){
        this.carDAO = carDAO;
    }
    
    public Car get(String license){
    	return carDAO.get(license);
    }

    public List<Car> getAll(){
        return carDAO.getAll();
    }
    
    public Response add(String id, Car car){
    	return carDAO.addCar(id, car);
    }
    
    public Response add(List<Car> cars){
    	return carDAO.addCars(cars);
    }
    
    public Response delete(Car car){
    	return carDAO.deleteCar(car);
    }
}
