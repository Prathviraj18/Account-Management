package com.capstone.accountManagementSystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.capstone.accountManagementSystem.dto.Account;
import com.capstone.accountManagementSystem.dto.AccountTransactions;
import com.capstone.accountManagementSystem.dto.Customer;
import com.capstone.accountManagementSystem.dto.User;
import com.capstone.accountManagementSystem.exception.CustomException;

public interface CustomerService {

    public Customer getDetailsByPan(String pancardNo);
	
	public List<Account> getDetailsByCustomerId(int customerId);
	
	public List<AccountTransactions> findByAccountNumber(long accountNumber);
	
	public ResponseEntity<?> cashOperation(long accNo, long amount, String type);
	
	public ResponseEntity<?> transferOperation(long accTo, long accFrom, int amount);
	
	public ResponseEntity<?> exportTransactions(long accNo, String date) throws CustomException;
}
