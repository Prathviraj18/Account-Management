package com.capstone.accountManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.accountManagementSystem.dto.AccountTransactions;
import com.capstone.accountManagementSystem.dto.Customer;
import com.capstone.accountManagementSystem.dto.User;
import com.capstone.accountManagementSystem.service.CustomerService;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@GetMapping("/transaction/{accountNumber}")
	public List<AccountTransactions> getTransaction(@PathVariable long accountNumber) {
		List<AccountTransactions> transaction = customerService.findByAccountNumber(accountNumber);
		return transaction;
	}

	@PostMapping("/adduserdetails")
	public ResponseEntity<Object> addUserDetails(@RequestBody Customer customerDetails){
		
		Customer bankCustomerDetailsfromDB =customerService.getDetailsByPan(customerDetails.getPancardNo());
		//if user does not exist -> create new one
		if(bankCustomerDetailsfromDB==null) {
		
			try {
				
				Customer customer = customerService.createNewCusomer(customerDetails);
			
				User newUser = createNewLoginCredentials(customer);
				
//				sendEmail(newUser,customerDetails);
				
				return ResponseEntity.created(null).body(customer);
				
			} catch (Exception e) {
				// TODO: handle exception
				return ResponseEntity.badRequest().body("data is not proper");
			}
		}
		else 
			//if user already exists -> create new account and add that account number to existing customerId
		{
			return ResponseEntity.badRequest().body("data is not proper");
		}
		
		
	}

	private User createNewLoginCredentials(Customer customer) {
		User newUser = new User();
		newUser.userId=customer.getCustomerId();
		newUser.password="India123";
		newUser.roleId=2;
		customerService.createUser(newUser);
		return newUser;
	}

}
