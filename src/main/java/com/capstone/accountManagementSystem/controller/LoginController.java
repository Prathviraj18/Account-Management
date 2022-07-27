package com.capstone.accountManagementSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	@CrossOrigin(origins = "file:///C:/Users/17056/Downloads/CapstoneBarclays/CapstoneBarclays/Views/Login.html")
	@GetMapping("/login")
	public ResponseEntity<Object> userLogin(@RequestParam("customerId") int customer_id,
            @RequestParam("password") String password) {        
        return loginService.login(customer_id,password);
    }
}
