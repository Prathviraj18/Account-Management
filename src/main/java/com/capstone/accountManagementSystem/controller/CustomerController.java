package com.capstone.accountManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.accountManagementSystem.dto.Account;
import com.capstone.accountManagementSystem.dto.AccountTransactions;
import com.capstone.accountManagementSystem.dto.Customer;
import com.capstone.accountManagementSystem.dto.User;
import com.capstone.accountManagementSystem.exception.CustomException;
import com.capstone.accountManagementSystem.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	

	@GetMapping("/transaction/{accountNumber}")
	public List<AccountTransactions> getTransaction(@PathVariable long accountNumber) {
		return customerService.findByAccountNumber(accountNumber);
	}

	@GetMapping("/cashOperation")
	public ResponseEntity<?> cashOperation(@RequestParam("accountNo") long accountNo,
			@RequestParam("amount") long amount, @RequestParam("type") String type) {
		return customerService.cashOperation(accountNo, amount, type);
	}

	@GetMapping("/getDetailsByCustomerId")
	public List<Account> getDetailsByCustomerId(@RequestParam("customerId") int customerId) {
		return customerService.getDetailsByCustomerId(customerId);
	}

	@GetMapping("/transferOperation")
	public ResponseEntity<?> transferOperation(@RequestParam("accountNoTo") long accountNoTo, @RequestParam("accountNoFrom") long accountNoFrom, @RequestParam("amount") int amount){
		return customerService.transferOperation(accountNoTo, accountNoFrom, amount);
	}
	
	@GetMapping("/exportTransactions")
	public ResponseEntity<?> exportTransactions(@RequestParam("accountNo") long accountNo, @RequestParam("date") String date) throws CustomException{
		return customerService.exportTransactions(accountNo, date);
	}
	
}
