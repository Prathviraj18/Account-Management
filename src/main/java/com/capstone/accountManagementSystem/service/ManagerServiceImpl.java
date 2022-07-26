package com.capstone.accountManagementSystem.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.accountManagementSystem.dto.Account;
import com.capstone.accountManagementSystem.dto.Customer;
import com.capstone.accountManagementSystem.repo.AccountRepository;
import com.capstone.accountManagementSystem.repo.CustomerRepository;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public long addAccount(Optional<Integer> custId){
		// TODO Auto-generated method stub
		
		Random rand = new Random();
		
		Account acc = new Account();

		
		long accNo = rand.nextLong(Long.parseLong("8999999999")) + 1000000000; 
		
		acc.setAccountNo(accNo);
		
		if(custId.isPresent()) {
			acc.setCustomerId(custId.get());
		}
		
		else {
		    int id = rand.nextInt(899999) + 100000;
			acc.setCustomerId(id);
		}
		acc.setCurrentBalance(0);
		System.out.println(acc.getAccountNo()+" "+acc.getCurrentBalance()+" "+acc.getCustomerId());
		accountRepository.save(acc);
		
		return accNo;
	}
	
	@Override
	public Optional<Customer> getCustomerByPan(String panId){
		// TODO Auto-generated method stub
		
		List<Customer> Res = customerRepository.findByPancardNo(panId);
		
		if(Res.size() == 1) {
			return Optional.of(Res.get(0));
		}
		return Optional.empty();
		}
}
