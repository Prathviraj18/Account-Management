package com.capstone.accountManagementSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capstone.accountManagementSystem.dto.Account;
import com.capstone.accountManagementSystem.repo.AccountRepository;

@SpringBootTest
class AccountManagementSystemApplicationTests {

	@Autowired
	private AccountRepository accountRepository;
	
	@Disabled
	@Test
	public void testFindByCustomerId () {
		Account account = new Account();
		account.setAccountNo(0000001);
		account.setCurrentBalance(10000013);
		account.setCustomerId(78965453);
		accountRepository.save(account);
		List<Account> data = accountRepository.findByCustomerId(789654);
		assertEquals(7896554,data.get(0).getCustomerId());
	}

	@Test
	public void testFindByAccountNo () {
		Account account = new Account();
		account.setAccountNo(666333);
		account.setCurrentBalance(20);
		account.setCustomerId(7214635);
		accountRepository.save(account);
		List<Account> data = accountRepository.findByAccountNo(0111111112);
		assertEquals(0111111112,data.get(0).getAccountNo());
	}

}
