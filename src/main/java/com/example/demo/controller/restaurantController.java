package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.repository.*;
import com.example.demo.model.restaurant;



@RestController
public class restaurantController {

	@Autowired
	restaurantRepository restaurantRepository;

		
	@GetMapping("/restaurant")
	public ResponseEntity<Object> getrestaurant() {
		
		try {
			
			List<restaurant> restaurants = restaurantRepository.findAll();
			return new ResponseEntity<>(restaurants, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
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
	public ResponseEntity<Object> getrestaurantDetail(@PathVariable Integer restaurantId ) {
		
		try {
			
			Optional<restaurant> restaurant = restaurantRepository.findById(restaurantId);
			if(restaurant.isPresent()) {
				return new ResponseEntity<>(restaurant, HttpStatus.OK);
			}else {
				return new ResponseEntity<>("restaurant Not Found", HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@PutMapping("/restaurant/{restaurantId}")
	public ResponseEntity<Object> updaterestaurant(@PathVariable Integer restaurantId,@RequestBody restaurant body) {
		
		try {
			Optional<restaurant> restaurant = restaurantRepository.findById(restaurantId);
			
			if(restaurant.isPresent()) {
				restaurant restaurantEdit = restaurant.get();
				restaurantEdit.setNamerestaurant(body.getNamerestaurant());
				restaurantEdit.setDetailrestaurant(body.getDetailrestaurant());
				
				restaurantRepository.save(restaurantEdit);
				
				return new ResponseEntity<>("EDIT SUCCESS", HttpStatus.OK);
			}else {
				
				return new ResponseEntity<>("User Not Found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping("/restaurant/{restaurantId}")
	public ResponseEntity<Object> deleterestaurant (@PathVariable Integer restaurantId) {
		
		try {
			Optional<restaurant> restaurant = restaurantRepository.findById(restaurantId);
			if(restaurant.isPresent()) {
				
				restaurantRepository.delete(restaurant.get());
				
				return new ResponseEntity<>("DELETE SUCCESS", HttpStatus.OK);
			}else {
				return new ResponseEntity<>("restaurant Not Found", HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
		
	
}