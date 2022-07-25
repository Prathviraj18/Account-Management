package com.capstone.accountManagementSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.accountManagementSystem.dto.Account;


public interface AccountRepository extends JpaRepository<Account, Integer>{

}
