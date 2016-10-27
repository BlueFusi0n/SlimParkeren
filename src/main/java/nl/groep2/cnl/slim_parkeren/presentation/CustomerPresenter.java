package nl.groep2.cnl.slim_parkeren.presentation;

import java.util.ArrayList;
import java.util.List;

import nl.groep2.cnl.slim_parkeren.model.Customer;
import nl.groep2.cnl.slim_parkeren.presentation.model.CustomerView;

public class CustomerPresenter extends BasePresenter {

	public CustomerView present( Customer customer )
    {
		CustomerView customerView = new CustomerView();
        
		customerView.id = customer.getId().toString();
		customerView.firstName = customer.getFirstName();
		customerView.lastName = customer.getLastName();
		customerView.email = customer.getEmail();
		customerView.phone= customer.getPhone();
		if(customer.getCar() != null)
			customerView.car = customer.getCar();
                
        return customerView;
    }

    public List<CustomerView> present( List<Customer> customers )
    {
        List<CustomerView> customerViews = new ArrayList<CustomerView>();
        
        for( Customer customer : customers )
        	customerViews.add( present( customer ) );
        
        return customerViews;
    }
}
