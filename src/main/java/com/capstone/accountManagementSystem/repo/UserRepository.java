package com.capstone.accountManagementSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.accountManagementSystem.dto.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
