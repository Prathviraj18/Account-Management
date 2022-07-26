package com.capstone.accountManagementSystem.service;

import java.util.List;

import com.capstone.accountManagementSystem.dto.AccountTransactions;
import com.capstone.accountManagementSystem.dto.Customer;
import com.capstone.accountManagementSystem.dto.User;

public interface CustomerService {

	public Customer getDetailsByPan(String pancardNo);

	public Customer createNewCusomer(Customer customerDetails);

	public void createUser(User newUser);

	public List<AccountTransactions> findByAccountNumber(long accountNumber);

}
