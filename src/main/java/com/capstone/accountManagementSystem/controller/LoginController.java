package com.capstone.accountManagementSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.accountManagementSystem.dto.User;
import com.capstone.accountManagementSystem.service.LoginService;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	LoginService loginService; 
	
	@GetMapping("/login")
	public ResponseEntity<Object> userLogin(@RequestParam("customer_id") int customer_id, @RequestParam("password") String password) {
		System.out.println(customer_id + password);
		
		 Optional<User> user = loginService.getUserById(customer_id);
		 
		 if(!user.isPresent()) {
			 return new ResponseEntity<>("User doesn't exist, contact Bank Manager", HttpStatus.OK);
		 }
		 else {
			 if(user.get().getPassword().equals(password)) {
				 System.out.println("User logged in");
				 return new ResponseEntity<>(user.get(), HttpStatus.OK);
			 }
		 }
		 return new ResponseEntity<>(false, HttpStatus.OK);
	}	
}
