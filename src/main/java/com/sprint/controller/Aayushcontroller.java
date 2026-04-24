package com.sprint.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class Aayushcontroller {
	@GetMapping("/hello1")
	public String hello() {
		return "Hello World from Aayushcontroller";
	}
	
	
}
