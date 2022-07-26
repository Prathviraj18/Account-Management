package com.capstone.accountManagementSystem.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.accountManagementSystem.dto.Customer;
import com.capstone.accountManagementSystem.service.ManagerService;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

	@Autowired
	ManagerService managerService;
	
	@PostMapping("/addAccount")
	public ResponseEntity<?> addAccount(int custId) {
		long userId = managerService.addAccount(Optional.of(custId));
		return ResponseEntity.ok(userId);		
	}
	
	@GetMapping("/getCustomerByPan")
	public ResponseEntity<?> getCustomerByPan(@RequestParam("panId") String panId) {
		Optional<Customer> Res = managerService.getCustomerByPan(panId);
		return ResponseEntity.ok(Res);	
	}
}
