package com.capstone.accountManagementSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capstone.accountManagementSystem.dto.User;
import com.capstone.accountManagementSystem.repo.UserRepository;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserRepository userRepository; 
	
	@Override
	public ResponseEntity<Object> login(int userId,String password) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            return new ResponseEntity<>("User doesn't exist, contact Bank Manager", HttpStatus.OK);
        } else {
            if (user.get().getPassword().equals(password)) {
                if(password.equals("India123")) {
                    return new ResponseEntity<>("Please change your password", HttpStatus.OK);
                }
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
}
