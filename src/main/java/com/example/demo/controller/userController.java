package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.jni.Sockaddr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.user;
import com.example.demo.repository.*;

@RestController
public class userController {

	@Autowired
	userRepository userRepository;
	
	
	private List<user> data = new ArrayList<user>();
	
	@GetMapping("/user")
	public ResponseEntity<Object> getuser() {
		try {
			List<user> user = userRepository.findAll();
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/user")
	public ResponseEntity<Object> addUser(@RequestBody user body) {
		
		try {
			user user = userRepository.save(body);
			
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} catch (Exception e) {
			
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	
	@GetMapping("/user/{Id}")
	public ResponseEntity<Object> getuserDetail(@PathVariable Integer Id) {
		try {
			
			Optional<user> user = userRepository.findById(Id);
			if (user.isPresent()) {
				return new ResponseEntity<>(user,HttpStatus.OK);
			}else {
				return new ResponseEntity<>("user not found",HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping("/user/{Id}")
	public ResponseEntity<Object> updateuser(@PathVariable Integer Id,@RequestBody user body) {
		
		try {
			Optional<user> user = userRepository.findById(Id);
			if(user.isPresent()) {
					user userEdit = user.get();
					userEdit.setFirstname(body.getFirstname());
					userEdit.setLastname(body.getLastname());
					userEdit.setUsername(body.getUsername());
					userEdit.setPassword(body.getPassword());
					
					userRepository.save(userEdit);
					
					return new ResponseEntity<>(user,HttpStatus.OK);
			}else {
				return new ResponseEntity<>("user not found",HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@DeleteMapping("/user/{Id}")
	public ResponseEntity<Object> deleteuser(@PathVariable Integer Id) {
		
		try {
			Optional<user> user = userRepository.findById(Id);
			
			if(user.isPresent()) {
				
				userRepository.delete(user.get());
				
				return new ResponseEntity<Object>("Delete Sucsess",HttpStatus.OK);
		
			}else {
				return new ResponseEntity<>("user not found",HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
}