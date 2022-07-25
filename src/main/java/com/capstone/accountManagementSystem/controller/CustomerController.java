package com.capstone.accountManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.accountManagementSystem.dto.Customer;
import com.capstone.accountManagementSystem.service.CustomerService;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/adduserdetails")
	public ResponseEntity<Object> addUserDetails(@RequestBody Customer customerDetails){
		
		Customer bankCustomerDetailsfromDB =customerService.getDetailsByPan(customerDetails.getPancardNo());
		//if user does not exist -> create new one
		if(bankCustomerDetailsfromDB==null) {
		
			try {
				
				Customer customer = customerService.createNewCusomer(customerDetails);
			
//				Users newUser = createNewLoginCredentials(bankCustomerDetails);
				
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

}
