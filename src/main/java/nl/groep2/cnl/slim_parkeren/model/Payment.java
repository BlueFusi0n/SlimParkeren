package nl.groep2.cnl.slim_parkeren.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.mongodb.morphia.annotations.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(value = "Payments", noClassnameStored = true)
public class Payment extends EntityModel {
	
	@NotEmpty
	@JsonProperty("Type")
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
