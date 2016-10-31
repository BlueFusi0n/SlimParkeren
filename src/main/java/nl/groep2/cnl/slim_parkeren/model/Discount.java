package nl.groep2.cnl.slim_parkeren.model;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.mongodb.morphia.annotations.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(value = "Discounts", noClassnameStored = true)
public class Discount extends EntityModel {
	
	@NotEmpty
	@JsonProperty("Code")
	private String code;

	@JsonProperty("ExpiryDate")
	private Date expiryDate;
	
	@JsonProperty("Amount")
	private int amount; //Amount is number of discountcodes left (-1 = infinite)
	
	@JsonProperty("Deal")
	private int deal; 	//Deal is number in percentage as discount
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getDeal() {
		return deal;
	}
	public void setDeal(int deal) {
		this.deal = deal;
	}
}

