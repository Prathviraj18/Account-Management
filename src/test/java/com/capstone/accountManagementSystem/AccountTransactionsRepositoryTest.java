package com.capstone.accountManagementSystem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.capstone.accountManagementSystem.dto.AccountTransactions;
import com.capstone.accountManagementSystem.repo.AccountTransactionsRepository;
@SpringBootTest
class AccountTransactionsRepositoryTest {
	

	@Autowired
	private AccountTransactionsRepository accountTransactionsRepository;
	
	//@Disabled
	@Test
	public void testFindByCustomerId () {
		 AccountTransactions  accountTransactions = new  AccountTransactions();
		 accountTransactions.setAccountNo(987656635);
		 accountTransactions.setCurrentBalance(35845445);
		 accountTransactions.setDateTime(null);
		 accountTransactions.setType("Saving Account");
		 accountTransactions.setSubtype("Current");
		 accountTransactions.setTransactionReferenceNo(88888888);
		 accountTransactions.setTransactionId(1122326636);
		 
		 accountTransactionsRepository.save(accountTransactions);
		//assertNotNull(accountRepository.findByCustomerId(789654));
		List<AccountTransactions> data = accountTransactionsRepository.findByAccountNo(987656635);
		assertEquals(987656635,data.get(0).getAccountNo());
	}


}
