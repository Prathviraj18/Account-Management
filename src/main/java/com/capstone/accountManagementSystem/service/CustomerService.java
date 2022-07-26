package com.capstone.accountManagementSystem.service;

import com.capstone.accountManagementSystem.dto.Customer;
import com.capstone.accountManagementSystem.dto.User;

public interface CustomerService {

	public Customer getDetailsByPan(String pancardNo);

	public Customer createNewCusomer(Customer customerDetails);

	public void createUser(User newUser);

}
