package nl.groep2.cnl.slim_parkeren.model;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class Discount extends EntityModel {
	
	private String Code;
	@NotEmpty
	private Date ExpiryDate;
	@NotEmpty
	private int Amount; //Amount is number of discountcodes left (-1 = infinite)
	@NotEmpty
	private int Deal; 	//Deal is number in percentage as discount
	
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public Date getExpiryDate() {
		return ExpiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		ExpiryDate = expiryDate;
	}
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
	public int getDeal() {
		return Deal;
	}
	public void setDeal(int deal) {
		Deal = deal;
	}
}
