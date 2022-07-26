package com.capstone.accountManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.accountManagementSystem.dto.Customer;
import com.capstone.accountManagementSystem.dto.User;
import com.capstone.accountManagementSystem.repo.CustomerRepository;
import com.capstone.accountManagementSystem.repo.UserRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository; 
	@Autowired
	UserRepository userRepository;
	
	public Customer getDetailsByPan(String pancardNo)
	{
       return customerRepository.findByPannumber(pancardNo);

	}
    public Customer createNewCusomer(Customer customerDetails) {
		
		
    	customerRepository.save(customerDetails);
		
		return customerDetails;
	}
	public void createUser(User user) {
		userRepository.save(user);	
	}
}
