package org.AC.dto;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "SERVICESANDSKILL")
public class ServicesAndSkill {
	
	this.service = service;
	}
	private Float price;
	private Number rating;
	
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Number getRating() {
		return rating;
	}
	public void setRating(Number rating) {
		this.rating = rating;
	}
}
