package com.capstone.accountManagementSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.accountManagementSystem.dto.AccountTransactions;

public interface AccountTransactionsRepository extends JpaRepository<AccountTransactions, Integer>{

}
