package com.capstone.accountManagementSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capstone.accountManagementSystem.dto.Account;
import com.capstone.accountManagementSystem.repo.AccountRepository;

@SpringBootTest
public class AccountRepositoryTest {

	
	@Autowired
	private AccountRepository accountRepository;
	
	
	
	//@Disabled
	@Test
	public void testFindByCustomerId () {
		Account account = new Account();
		account.setAccountNo(236598);
		account.setCurrentBalance(1011043);
		account.setCustomerId(75613);
		accountRepository.save(account);
		//assertNotNull(accountRepository.findByCustomerId(789654));
		List<Account> data = accountRepository.findByCustomerId(75613);
		assertEquals(75613,data.get(0).getCustomerId());
	}

	//@Disabled
	@Test
	public void testFindByAccountNo () {
		Account account = new Account();
		account.setAccountNo(987654);
		account.setCurrentBalance(8952);
		account.setCustomerId(896541);
		accountRepository.save(account);
		List<Account> data = accountRepository.findByAccountNo(987654);
		//assertNotNull(accountRepository.findByAccountNo(0161111112));
		assertEquals(987654,data.get(0).getAccountNo());
	}
}
