package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.jni.Sockaddr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.restaurant;
import com.example.demo.repository.*;

@RestController
@CrossOrigin(origins ="*")
public class restaurantController {

	@Autowired
	restaurantRepository restaurantRepository;
	
	
	private List<restaurant> data = new ArrayList<restaurant>();
	
	@GetMapping("/restaurant")
	public ResponseEntity<Object> getrestaurant() {
		try {
			List<restaurant> restaurant = restaurantRepository.findAll();
			return new ResponseEntity<>(restaurant, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/restaurant")
	public ResponseEntity<Object> addrestaurant(@RequestBody restaurant body) {
		
		try {
			restaurant restaurant = restaurantRepository.save(body);
			
			return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
		} catch (Exception e) {
			
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	
	@GetMapping("/restaurant/{restaurantId}")
	public ResponseEntity<Object> getrestaurantDetail(@PathVariable Integer restaurantId) {
		try {
			
			Optional<restaurant> restaurant = restaurantRepository.findById(restaurantId);
			if (restaurant.isPresent()) {
				return new ResponseEntity<>(restaurant,HttpStatus.OK);
			}else {
				return new ResponseEntity<>("restaurant not found",HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping("/restaurant/{restaurantId}")
	public ResponseEntity<Object> updaterestaurant(@PathVariable Integer restaurantId,@RequestBody restaurant body) {
		
		try {
			Optional<restaurant> restaurant = restaurantRepository.findById(restaurantId);
			if(restaurant.isPresent()) {
					restaurant restaurantEdit = restaurant.get();
					restaurantEdit.setRestaurantname(body.getRestaurantname());
					restaurantEdit.setRestaurantdetail(body.getRestaurantdetail());
					
					restaurantRepository.save(restaurantEdit);
					
					return new ResponseEntity<>(restaurant,HttpStatus.OK);
			}else {
				return new ResponseEntity<>("restaurant not found",HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@DeleteMapping("/restaurant/{restaurantId}")
	public ResponseEntity<Object> deleterestaurant(@PathVariable Integer restaurantId) {
		
		try {
			Optional<restaurant> restaurant = restaurantRepository.findById(restaurantId);
			
			if(restaurant.isPresent()) {
				
				restaurantRepository.delete(restaurant.get());
				
				return new ResponseEntity<Object>("Delete Sucsess",HttpStatus.OK);
		
			}else {
				return new ResponseEntity<>("restaurant not found",HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
}