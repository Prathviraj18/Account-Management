package com.capstone.accountManagementSystem.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capstone.accountManagementSystem.dto.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Query(value = "select * from Customer where pancardNo =?1",nativeQuery = true)
	Customer findByPannumber(String pannumber);
	
	List<Customer> findByPancardNo(String pancardNo);
	 
}
