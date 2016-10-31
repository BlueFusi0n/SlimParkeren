package nl.groep2.cnl.slim_parkeren.presentation;

import java.util.ArrayList;
import java.util.List;

import nl.groep2.cnl.slim_parkeren.model.Car;
import nl.groep2.cnl.slim_parkeren.presentation.model.CarView;

public class CarPresenter extends BasePresenter {
	
	public CarView present(Car car){	
		CarView carView = new CarView();
        
		carView.brand = car.getBrand();
		carView.color = car.getColor();
		carView.licensePlate = car.getLicensePlate();
		carView.type = car.getType();    
		if(car.getCustomerId() != null)
			carView.customerId = car.getCustomerId();
        return carView;
    }

    public List<CarView> present( List<Car> cars )
    {
        List<CarView> carViews = new ArrayList<CarView>();
        if(cars == null) return null;
        for( Car car : cars)
        	carViews.add(present(car));
        
        return carViews;
    }

}
