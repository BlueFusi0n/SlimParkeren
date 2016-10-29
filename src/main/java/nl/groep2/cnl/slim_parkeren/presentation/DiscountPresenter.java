package nl.groep2.cnl.slim_parkeren.presentation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import nl.groep2.cnl.slim_parkeren.model.Discount;
import nl.groep2.cnl.slim_parkeren.presentation.model.DiscountView;

public class DiscountPresenter {
	
	public DiscountView present( Discount discount)
    {
		DiscountView discountView = new DiscountView();
		
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
		
		discountView.id = discount.getId().toString();
		discountView.code = discount.getCode();
		discountView.expiryDate = df.format(discount.getExpiryDate());
		discountView.amount = discount.getAmount();
		discountView.deal = discount.getDeal(); 
                
        return discountView;
    }
	
    public List<DiscountView> present( List<Discount> discounts )
    {
        List<DiscountView> discountViews = new ArrayList<DiscountView>();
        
        for( Discount discount : discounts )
        	discountViews.add( present( discount ) );
        
        return discountViews;
    }

}
