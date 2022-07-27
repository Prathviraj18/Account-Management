package com.capstone.accountManagementSystem.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.capstone.accountManagementSystem.dto.Customer;
import com.capstone.accountManagementSystem.dto.User;

public interface ManagerService {

	public long addAccount(Optional<Integer> custId);
	
	public Optional<Customer> getCustomerByPan(String panId);
	
	public ResponseEntity<Object> addUser(Customer customerDetails);
}
