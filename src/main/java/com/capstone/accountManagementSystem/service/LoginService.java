package com.capstone.accountManagementSystem.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.capstone.accountManagementSystem.dto.User;

public interface LoginService {

	public ResponseEntity<Object> login(int userId,String password);
}
