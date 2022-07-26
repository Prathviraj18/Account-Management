package com.capstone.accountManagementSystem.service;

import java.util.Optional;

import com.capstone.accountManagementSystem.dto.Customer;

public interface ManagerService {

	public long addAccount(Optional<Integer> custId);
	
	public Optional<Customer> getCustomerByPan(String panId);
}
