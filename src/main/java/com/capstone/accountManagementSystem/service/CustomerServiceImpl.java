package com.capstone.accountManagementSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.accountManagementSystem.dto.AccountTransactions;
import com.capstone.accountManagementSystem.dto.Customer;
import com.capstone.accountManagementSystem.dto.User;
import com.capstone.accountManagementSystem.repo.AccountTransactionsRepository;
import com.capstone.accountManagementSystem.repo.CustomerRepository;
import com.capstone.accountManagementSystem.repo.UserRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository; 
	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountTransactionsRepository accountRepository;
	
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
	@Override
	public List<AccountTransactions> findByAccountNumber(long accountNumber) {
		return accountRepository.findByAccountNumber(accountNumber);
	}
}
