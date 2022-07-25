package com.capstone.accountManagementSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.accountManagementSystem.dto.User;
import com.capstone.accountManagementSystem.repo.UserRepository;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserRepository userRepository; 
	
	@Override
	public Optional<User> getUserById(int userId) {
		// TODO Auto-generated method stub
		
		return userRepository.findById(userId);
	}
}
