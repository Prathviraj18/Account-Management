package com.capstone.accountManagementSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.accountManagementSystem.dto.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
