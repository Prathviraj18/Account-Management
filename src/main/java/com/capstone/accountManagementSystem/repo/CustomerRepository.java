package com.capstone.accountManagementSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capstone.accountManagementSystem.dto.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Query(value = "select * from Customer where pan_number =?1",nativeQuery = true)
	Customer findByPannumber(String pannumber);
}
