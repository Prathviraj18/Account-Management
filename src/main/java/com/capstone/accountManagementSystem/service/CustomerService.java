package com.capstone.accountManagementSystem.service;

import java.util.List;

import com.capstone.accountManagementSystem.dto.Account;
import com.capstone.accountManagementSystem.dto.AccountTransactions;
import com.capstone.accountManagementSystem.dto.Customer;
import com.capstone.accountManagementSystem.dto.User;

public interface CustomerService {


	public Customer getDetailsByPan(String pancardNo);

	public Customer createNewCusomer(Customer customerDetails);
	
	public List<Account> getDetailsByCustomerId(int customerId);
	
	public void createUser(User newUser);
	
	public List<AccountTransactions> findByAccountNumber(long accountNumber);
	
	public long cashOperation(long accNo, long amount, String type);
	
	public void sendCredentialsMail(User user, Customer customer);
	
	public User createNewLoginCredentials(Customer customer);
	
	public long transferOperation(long accTo, long accFrom, int amount);
}
