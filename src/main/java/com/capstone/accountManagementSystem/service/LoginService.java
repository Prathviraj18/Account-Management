package com.capstone.accountManagementSystem.service;

import java.util.Optional;

import com.capstone.accountManagementSystem.dto.User;

public interface LoginService {

	public Optional<User> getUserById(int userId);
}
