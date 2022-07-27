package com.capstone.accountManagementSystem;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capstone.accountManagementSystem.dto.AccountTransactions;
import com.capstone.accountManagementSystem.dto.Customer;
import com.capstone.accountManagementSystem.repo.CustomerRepository;
@SpringBootTest
class CustomerRepositoryTest {


	@Autowired
	private CustomerRepository customerRepository;
	
	DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
    LocalDate date = LocalDate.parse("20200727", formatter);
	
	//@Disabled
	@Test
	public void testFindByPanNo () {
		Customer  customer = new  Customer();
		customer.setAadharDoc("abcde");
		customer.setAadharNo(456666646);
		customer.setAddress("Indore,Madhya Pradesh");
		customer.setCustomerId(8520852);
		customer.setCustomerName("Sachin");
		customer.setDob(date);
		customer.setEmail("xyz@gmail.com");
		customer.setPancardDoc("http://www.pancard.com");
		customer.setPancardNo("AB2525YUIO");
		customerRepository.save(customer);
		//assertNotNull(accountRepository.findByCustomerId(789654));
		List<Customer> data = customerRepository.findByPancardNo("AB2525YUIO");
		assertEquals("AB2525YUIO",data.get(0).getPancardNo());
	}


}
