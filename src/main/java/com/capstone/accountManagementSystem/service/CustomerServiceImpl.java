package com.capstone.accountManagementSystem.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import com.capstone.accountManagementSystem.dto.Account;
import com.capstone.accountManagementSystem.dto.AccountTransactions;
import com.capstone.accountManagementSystem.dto.Customer;
import com.capstone.accountManagementSystem.dto.User;
import com.capstone.accountManagementSystem.repo.AccountRepository;
import com.capstone.accountManagementSystem.repo.AccountTransactionsRepository;
import com.capstone.accountManagementSystem.repo.CustomerRepository;
import com.capstone.accountManagementSystem.repo.UserRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository; 
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountTransactionsRepository accountTransactionsRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public Customer getDetailsByPan(String pancardNo)
	{
       return customerRepository.findByPannumber(pancardNo);

	}
    public Customer createNewCusomer(Customer customerDetails) {


    	customerRepository.save(customerDetails);

		return customerDetails;
	}
    public List<Account> getDetailsByCustomerId(int customerId) {
    	return accountRepository.findByCustomerId(customerId);
    }
    
    public void createUser(User user) {
		userRepository.save(user);	
	}
    @Override
	public List<AccountTransactions> findByAccountNumber(long accountNumber) {
		return accountTransactionsRepository.findByAccountNo(accountNumber);
	}
    
    @Override
	public long cashOperation(long accNo, long amount, String type) {
		List<Account> accounts = accountRepository.findByAccountNo(accNo);
		if(accounts.size() == 1) {
			Account acc = accounts.get(0);
			
			long currBalance = acc.getCurrentBalance();
			
			if(type.equalsIgnoreCase("credit")) {
				currBalance += amount;
			}
			else if(type.equalsIgnoreCase("debit")) {
				if(currBalance > amount)
					currBalance -= amount;
				else
					return Long.parseLong("-1");
			}
			else {
				return Long.parseLong("-1");
			}
			acc.setCurrentBalance(currBalance);
			
			AccountTransactions accTransact = new AccountTransactions();
			
			Random rand = new Random();
			
			long transactNo = rand.nextLong(Long.parseLong("89999999999") + Long.parseLong("10000000000"));
			
			accTransact.setAccountNo(accNo);
			accTransact.setCurrentBalance(currBalance);
			accTransact.setSubtype("CASH");
			accTransact.setType(type.toUpperCase());
			accTransact.setDateTime(new Date());
			accTransact.setTransactionReferenceNo(transactNo);
			accTransact.setTransactionId(rand.nextInt(100000));
			
			accountTransactionsRepository.save(accTransact);
			
			return currBalance;
			
		}
		return Long.parseLong("-1");
	}
    
    @Override
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
    
    @Override
	public long transferOperation(long accTo, long accFrom, int amount) {
		List<Account> accountsFrom = accountRepository.findByAccountNo(accFrom);
		List<Account> accountsTo = accountRepository.findByAccountNo(accTo);
		if(accountsFrom.size() == 1 && accountsTo.size() == 1) {
			Account objFrom = accountsFrom.get(0);
			Account objTo = accountsTo.get(0);
			
			long fromBalance = objFrom.getCurrentBalance();
			long toBalance = objFrom.getCurrentBalance();
			
			if(fromBalance > amount ) {
				fromBalance -= amount;
				objFrom.setCurrentBalance(fromBalance);
				toBalance += amount;
				objTo.setCurrentBalance(toBalance + amount);
				
				AccountTransactions accTransactFrom = new AccountTransactions();
				AccountTransactions accTransactTo = new AccountTransactions();
				Random rand = new Random();
				
				long transactNoFrom = rand.nextLong(Long.parseLong("89999999999") + Long.parseLong("10000000000"));
				long transactNoTo = rand.nextLong(Long.parseLong("89999999999") + Long.parseLong("10000000000"));
				
				accTransactFrom.setAccountNo(accFrom);
				accTransactFrom.setCurrentBalance(fromBalance);
				accTransactFrom.setSubtype("TRANSFER");
				accTransactFrom.setType("DEBIT");
				accTransactFrom.setDateTime(new Date());
				accTransactFrom.setTransactionReferenceNo(transactNoFrom);
				accTransactFrom.setTransactionId(rand.nextInt(100000));
				
				accTransactTo.setAccountNo(accTo);
				accTransactTo.setCurrentBalance(toBalance);
				accTransactTo.setSubtype("TRANSFER");
				accTransactTo.setType("CREDIT");
				accTransactTo.setDateTime(new Date());
				accTransactTo.setTransactionReferenceNo(transactNoTo);
				accTransactTo.setTransactionId(rand.nextInt(100000));
				
				accountTransactionsRepository.save(accTransactFrom);
				accountTransactionsRepository.save(accTransactTo);
				
				sendTransactionMail(customerRepository.findById(objFrom.getCustomerId()), accTransactFrom, fromBalance, "debit");
				sendTransactionMail(customerRepository.findById(objTo.getCustomerId()), accTransactTo, toBalance, "credit");
				
				return fromBalance;
			}
			else {
				return Long.parseLong("-1");
			}	
		}
		else {
			return Long.parseLong("-1");
		}
	}
	
	public void sendTransactionMail(Optional<Customer> optional, AccountTransactions accTransact , long fromBalance, String type) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(optional.get().getEmail());
        msg.setSubject("Transaction alert for your bank account");msg.setText("Hi " + optional.get().getCustomerName()
        + ",\nThis email is sent to you to inform you of a transaction of " + String.valueOf(fromBalance) + " of type " + type
        + " took place on your account at " + String.valueOf(accTransact.getDateTime()) + ", having transaction ref. number: " + accTransact.getTransactionReferenceNo() + "\n \n"
        + "Thank You for Registering");
        javaMailSender.send(msg);
    }
}
