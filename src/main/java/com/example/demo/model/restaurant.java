package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="restaurant")
public class restaurant {
 
	@Id
	private Integer restaurantId;
	private String  restaurantname;
	private String  restaurantdetail;
	
	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantname() {
		return restaurantname;
	}
	public void setRestaurantname(String restaurantname) {
		this.restaurantname = restaurantname;
	}
	public String getRestaurantdetail() {
		return restaurantdetail;
	}
	public void setRestaurantdetail(String restaurantdetail) {
		this.restaurantdetail = restaurantdetail;
	}
	
	
}