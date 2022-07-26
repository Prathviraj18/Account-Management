package com.capstone.accountManagementSystem.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.capstone.accountManagementSystem.dto.AccountTransactions;

public interface AccountTransactionsRepository extends JpaRepository<AccountTransactions, Integer>{

	@Query(value="select * from transaction where account_number=?1 order by transactiontime desc limit 0,5", nativeQuery=true)
	List<AccountTransactions> findByAccountNumber(long accountNumber);
}
