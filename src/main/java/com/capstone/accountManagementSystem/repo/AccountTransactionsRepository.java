package com.capstone.accountManagementSystem.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capstone.accountManagementSystem.dto.AccountTransactions;

public interface AccountTransactionsRepository extends JpaRepository<AccountTransactions, Integer>{

	@Query(value="select * from account_transactions where account_no=?1 order by date_time desc limit 0,5", nativeQuery=true)
	List<AccountTransactions> findByAccountNo(long accountNumber);
	
	@Query(value = "select * from account_transactions where account_no = :accountNo and date(date_time) = DATE( :queryDate ) ", nativeQuery = true) //
	List<AccountTransactions> findByAccountDate(@Param("accountNo") long accountNo, @Param("queryDate") Date queryDate);
}
