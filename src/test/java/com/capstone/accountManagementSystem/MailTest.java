package com.capstone.accountManagementSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.LogManager;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capstone.accountManagementSystem.dto.AccountTransactions;
import com.capstone.accountManagementSystem.dto.Customer;
import com.capstone.accountManagementSystem.dto.User;
import com.capstone.accountManagementSystem.repo.AccountTransactionsRepository;
import com.capstone.accountManagementSystem.service.CustomerServiceImpl;
import com.capstone.accountManagementSystem.service.ManagerServiceImpl;

@SpringBootTest
public class MailTest {

	
	@Autowired
	private ManagerServiceImpl managerServiceImpl;
	
	
	User user = new User();
	Customer customer = new Customer();
	
	DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
    LocalDate date = LocalDate.parse("20200727", formatter);

	
	//@Disabled
	@Test
	public void testFindByCustomerId () {
		user.setPassword("1234567");
		user.setRoleId(1);
		user.setUserId(123456);
		customer.setAadharDoc("abcde");
		customer.setAadharNo(456666646);
		customer.setAddress("Indore,Madhya Pradesh");
		customer.setCustomerId(8520852);
		customer.setCustomerName("Sachin");
		customer.setDob(date);
		customer.setEmail("shreyasharma121999@gmail.com");
		customer.setPancardDoc("http://www.pancard.com");
		customer.setPancardNo("AB2525YUIO");
		try {
		managerServiceImpl.sendCredentialsMail(user, customer);
		System.out.println("Mail Sent Successfullly");
		}
		catch(Exception e) {
			System.out.println("Mail Failed error is: "+e);
		}
		//customerServiceImpl.sendCredentialsMail(user, customer);
		//assertEquals(null,customerServiceImpl.sendCredentialsMail(user, customer));
		
	}

}
