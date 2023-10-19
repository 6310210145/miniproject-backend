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
	private String namerestaurant;
	private String detailrestaurant;
	private String review;
	
	
	public String getReview() {
		return review;
	}


	public void setReview(String review) {
		this.review = review;
	}


	/*@OneToMany(mappedBy = "restaurant")
	private List<Review> reviews;*/
	
	
	public restaurant() {
		super();
	}


	public restaurant(Integer restaurantId, String namerestaurant, String pricerestaurant, String amountrestaurant,
			String detailrestaurant) {
		super();
		this.restaurantId = restaurantId;
		this.namerestaurant = namerestaurant;
		this.detailrestaurant = detailrestaurant;
	}


	/*public restaurant(List<Review> reviews) {
		super();
		this.reviews = reviews;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}*/


	public Integer getrestaurantId() {
		return restaurantId;
	}


	public void setrestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}


	public String getNamerestaurant() {
		return namerestaurant;
	}


	public void setNamerestaurant(String namerestaurant) {
		this.namerestaurant = namerestaurant;
	}


	public String getDetailrestaurant() {
		return detailrestaurant;
	}


	public void setDetailrestaurant(String detailrestaurant) {
		this.detailrestaurant = detailrestaurant;
	}
	
	
	
	
}