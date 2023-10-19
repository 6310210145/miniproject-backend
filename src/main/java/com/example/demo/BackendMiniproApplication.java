package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendMiniproApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendMiniproApplication.class, args);
		System.out.println("Hello spring");
		System.out.println("Spring boot");
	}

}