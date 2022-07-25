package com.capstone.accountManagementSystem.service;

import com.capstone.accountManagementSystem.dto.Customer;

public interface CustomerService {

	public Customer getDetailsByPan(String pancardNo);

	public Customer createNewCusomer(Customer customerDetails);

}
