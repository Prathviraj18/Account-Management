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
import com.capstone.accountManagementSystem.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/adduserdetails")
	public ResponseEntity<Object> addUserDetails(@RequestBody Customer customerDetails) {

		try {
			Customer customer = customerService.createNewCusomer(customerDetails);
			User newUser = customerService.createNewLoginCredentials(customer);
			customerService.sendCredentialsMail(newUser, customerDetails);
			return ResponseEntity.created(null).body(customer);
		} catch (Exception e) { // TODO: handle exception
			return ResponseEntity.badRequest().body("data is not proper");
		}

	}

	@GetMapping("/transaction/{accountNumber}")
	public List<AccountTransactions> getTransaction(@PathVariable long accountNumber) {
		List<AccountTransactions> transaction = customerService.findByAccountNumber(accountNumber);
		return transaction;
	}

	

	@GetMapping("/cashOperation")
	public ResponseEntity<?> cashOperation(@RequestParam("accountNo") long accountNo,
			@RequestParam("amount") long amount, @RequestParam("type") String type) {
		long Res = customerService.cashOperation(accountNo, amount, type);
		if (Res > 0)
			return ResponseEntity.ok(Res);
		else
			return ResponseEntity.ok(null);
	}

	@GetMapping("/getDetailsByCustomerId")
	public List<Account> getDetailsByCustomerId(@RequestParam("customerId") int customerId) {
		List<Account> accounts = customerService.getDetailsByCustomerId(customerId);
		return accounts;
	}

	@GetMapping("/transferOperation")
	public ResponseEntity<?> transferOperation(@RequestParam("accountNoTo") long accountNoTo, @RequestParam("accountNoFrom") long accountNoFrom, @RequestParam("amount") int amount){
		long Res = customerService.transferOperation(accountNoTo, accountNoFrom, amount);
		if(Res > 0) 
			return ResponseEntity.ok(Res);
		else
			return ResponseEntity.ok(null);
	}
	
}
