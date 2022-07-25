package com.capstone.accountManagementSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.accountManagementSystem.dto.User;
import com.capstone.accountManagementSystem.service.LoginService;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	LoginService loginService; 
	
	@GetMapping("/login")
	public ResponseEntity<?> getUserById() {
		
		Optional<User> user = loginService.getUserById(1);
		
		if(user.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(user);
		// list ===> to json array 
	}
	
	
}
