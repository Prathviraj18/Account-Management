package com.capstone.accountManagementSystem.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.capstone.accountManagementSystem.dto.Account;
import com.capstone.accountManagementSystem.dto.Customer;
import com.capstone.accountManagementSystem.dto.User;
import com.capstone.accountManagementSystem.repo.AccountRepository;
import com.capstone.accountManagementSystem.repo.CustomerRepository;
import com.capstone.accountManagementSystem.repo.UserRepository;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	UserRepository userRepository;

	@Override
	public long addAccount(Optional<Integer> custId) {
		Random rand = new Random();
		Account acc = new Account();
		long accNo = rand.nextLong(Long.parseLong("8999999999")) + 1000000000;
		acc.setAccountNo(accNo);
		if (custId.isPresent()) {
			acc.setCustomerId(custId.get());
		} else {
			int id = rand.nextInt(899999) + 100000;
			acc.setCustomerId(id);
		}
		acc.setCurrentBalance(0);
		System.out.println(acc.getAccountNo() + " " + acc.getCurrentBalance() + " " + acc.getCustomerId());
		accountRepository.save(acc);

		return accNo;
	}

	@Override
	public Optional<Customer> getCustomerByPan(String panId) {
		List<Customer> Res = customerRepository.findByPancardNo(panId);
		if (Res.size() == 1) {
			return Optional.of(Res.get(0));
		}
		return Optional.empty();
	}

	public void sendCredentialsMail(User user, Customer customer) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(customer.getEmail());
		msg.setSubject("Welcome to AMS");
		msg.setText("Hi" + customer.getCustomerName()
				+ ",\nWelocome to AMS ,Your User Id and first time login Password is given below \nPlease make sure you change it on first login "
				+ " User ID: " + customer.getCustomerId() + " \nPassword:" + user.getPassword() + "\n \n"
				+ "Thank You for Registering");
		javaMailSender.send(msg);

	}

	public User createNewLoginCredentials(Customer customer) {
		User newUser = new User();
		newUser.setUserId(customer.getCustomerId());
		newUser.setPassword("India123");
		newUser.setRoleId(2);
		createUser(newUser);
		return newUser;
	}

	public void createUser(User user) {
		userRepository.save(user);
	}

	public Customer createNewCustomer(Customer customerDetails) {
		customerRepository.save(customerDetails);
		return customerDetails;
	}

	@Override
	public ResponseEntity<Object> addUser(Customer customerDetails) {
		try {
			Customer customer = createNewCustomer(customerDetails);
			User newUser = createNewLoginCredentials(customer);
			sendCredentialsMail(newUser, customerDetails);
			return ResponseEntity.created(null).body(customer);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("data is not proper");
		}
	}

}
