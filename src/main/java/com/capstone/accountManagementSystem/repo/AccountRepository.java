package com.capstone.accountManagementSystem.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capstone.accountManagementSystem.dto.Account;


public interface AccountRepository extends JpaRepository<Account, Integer>{

	//@Query(value = "select * from Account where customerId =?1",nativeQuery = true)
	List<Account> findByCustomerId(int customerId);
	
	List<Account> findByAccountNo(long accountNo);
}
