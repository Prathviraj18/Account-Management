package com.capstone.accountManagementSystem.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.accountManagementSystem.dto.Customer;
import com.capstone.accountManagementSystem.dto.User;
import com.capstone.accountManagementSystem.service.ManagerService;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

	@Autowired
	ManagerService managerService;
	
	@PostMapping("/adduserdetails")
	public ResponseEntity<Object> addUserDetails(@RequestBody Customer customerDetails) {
		return managerService.addUser(customerDetails);
	}
	
	@PostMapping("/addAccount")
	public ResponseEntity<?> addAccount(int custId) {
		return ResponseEntity.ok(managerService.addAccount(Optional.of(custId)));
	}
	
	@GetMapping("/getCustomerByPan")
	public ResponseEntity<?> getCustomerByPan(@RequestParam("panId") String panId) {
		return ResponseEntity.ok(managerService.getCustomerByPan(panId));
	} 
}
