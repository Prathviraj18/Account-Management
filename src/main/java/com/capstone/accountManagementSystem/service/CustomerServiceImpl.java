package com.capstone.accountManagementSystem.service;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import com.capstone.accountManagementSystem.dto.Account;
import com.capstone.accountManagementSystem.dto.AccountTransactions;
import com.capstone.accountManagementSystem.dto.Customer;
import com.capstone.accountManagementSystem.dto.User;
import com.capstone.accountManagementSystem.exception.CustomException;
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
	
	public Customer getDetailsByPan(String pancardNo) {
		return customerRepository.findByPannumber(pancardNo);

	}

	public List<Account> getDetailsByCustomerId(int customerId) {
		return accountRepository.findByCustomerId(customerId);
	}

	@Override
	public List<AccountTransactions> findByAccountNumber(long accountNumber) {
		return accountTransactionsRepository.findByAccountNo(accountNumber);
	}

	@Override
	public ResponseEntity<?> cashOperation(long accNo, long amount, String type) {
		List<Account> accounts = accountRepository.findByAccountNo(accNo);
		if (accounts.size() == 1) {
			Account acc = accounts.get(0);
			long currBalance = acc.getCurrentBalance();
			if (type.equalsIgnoreCase("credit")) {
				currBalance += amount;
			} else if (type.equalsIgnoreCase("debit")) {
				if (currBalance > amount)
					currBalance -= amount;
				else
					return ResponseEntity.badRequest().body("Current balance is less than the amount");
			} else {
				return ResponseEntity.badRequest().body("Type should be CREDIT/DEBIT");
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

			if (currBalance > 0)
				return ResponseEntity.ok(currBalance);
			else
				return ResponseEntity.ok(null);
		}
		return ResponseEntity.badRequest().body("Account not found");
	}

	@Override
	public ResponseEntity<?> transferOperation(long accTo, long accFrom, int amount) {
		List<Account> accountsFrom = accountRepository.findByAccountNo(accFrom);
		List<Account> accountsTo = accountRepository.findByAccountNo(accTo);
		if (accountsFrom.size() == 1 && accountsTo.size() == 1) {
			Account objFrom = accountsFrom.get(0);
			Account objTo = accountsTo.get(0);
			long fromBalance = objFrom.getCurrentBalance();
			long toBalance = objFrom.getCurrentBalance();

			if (fromBalance > amount) {
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

				sendTransactionMail(customerRepository.findById(objFrom.getCustomerId()), accTransactFrom, fromBalance,
						"debit");
				sendTransactionMail(customerRepository.findById(objTo.getCustomerId()), accTransactTo, toBalance,
						"credit");

				if (fromBalance > 0)
					return ResponseEntity.ok(fromBalance);
				else
					return ResponseEntity.ok(null);
			} else {
				return ResponseEntity.badRequest().body("Insuficient funds");
			}
		} else {
			return ResponseEntity.badRequest().body("Account not found");
		}
	}

	public void sendTransactionMail(Optional<Customer> optional, AccountTransactions accTransact, long fromBalance,
			String type) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(optional.get().getEmail());
		msg.setSubject("Transaction alert for your bank account");
		msg.setText("Hi " + optional.get().getCustomerName()
				+ ",\nThis email is sent to you to inform you of a transaction of " + String.valueOf(fromBalance)
				+ " of type " + type + " took place on your account at " + String.valueOf(accTransact.getDateTime())
				+ ", having transaction ref. number: " + accTransact.getTransactionReferenceNo() + "\n \n"
				+ "Thank You for Registering");
		javaMailSender.send(msg);
	}

	@Override
	public ResponseEntity<?> exportTransactions(long accNo, String date) throws CustomException {
		Date queryDate = new Date();
		try {
			queryDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		} catch (Exception e) {
			throw new CustomException("Date format incorrect, please enter String in dd/mm/yy format");
		}
		List<AccountTransactions> transactions = accountTransactionsRepository.findByAccountDate(accNo, queryDate);
		String DELIMITER = ",";
		String SEPARATOR = "\n";
		String HEADER = "TransactionId, TransactionReferenceNo, AccountNo, DateTime, Type, SubType, CurrentBalance";
		FileWriter file = null;
		try {
			String path = "src/exports/";
			String filename = "Export" + new SimpleDateFormat("dd_MM_yyyy").format(new Date()) + ".csv";
			file = new FileWriter(path + filename);
			file.append(HEADER);
			file.append(SEPARATOR);
			Iterator<AccountTransactions> it = transactions.iterator();
			while (it.hasNext()) {
				AccountTransactions a = (AccountTransactions) it.next();
				file.append(String.valueOf(a.getTransactionId()));
				file.append(DELIMITER);
				file.append(String.valueOf(a.getTransactionReferenceNo()));
				file.append(DELIMITER);
				file.append(String.valueOf(a.getAccountNo()));
				file.append(DELIMITER);
				file.append(String.valueOf(a.getDateTime()));
				file.append(DELIMITER);
				file.append(String.valueOf(a.getType()));
				file.append(DELIMITER);
				file.append(String.valueOf(a.getSubtype()));
				file.append(DELIMITER);
				file.append(String.valueOf(a.getCurrentBalance()));
				file.append(SEPARATOR);
			}
			file.close();
			return ResponseEntity.ok(filename);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			System.out.println(e.getLocalizedMessage());
			throw new CustomException("Error while writing to file!");
		}
	}
}
