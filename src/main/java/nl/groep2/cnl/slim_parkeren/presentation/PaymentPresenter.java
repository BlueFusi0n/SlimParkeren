package nl.groep2.cnl.slim_parkeren.presentation;

import java.util.ArrayList;
import java.util.List;

import nl.groep2.cnl.slim_parkeren.model.Payment;
import nl.groep2.cnl.slim_parkeren.presentation.model.PaymentView;

public class PaymentPresenter {
	
	public PaymentView present(Payment payment)
    {
		PaymentView paymentView = new PaymentView();
				
		paymentView.id = payment.getId().toString();
		paymentView.type = payment.getType();
                
        return paymentView;
    }
	
    public List<PaymentView> present( List<Payment> payments )
    {
        List<PaymentView> paymentViews = new ArrayList<PaymentView>();
        
        for( Payment payment : payments)
        	paymentViews.add( present( payment ) );
        
        return paymentViews;
    }

}
