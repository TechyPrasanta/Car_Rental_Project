package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SignupRequest;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.repository.UserRepo;

@Service
public class AuthServiceImpl implements AuthService {

@Autowired
UserRepo userRepo;

@Override
public UserDTO createcustomer(SignupRequest signupRequest) {
	User user = new User();
	user.setName(signupRequest.getName());
	user.setEmail(signupRequest.getEmail());
	user.setPassword(signupRequest.getPassword());
	user.setUserRole(UserRole.Customer);
	User createuser = userRepo.save(user);
	UserDTO userDTO = new UserDTO();
	userDTO.setId(createuser.getId());
	return userDTO;
}

@Override
public boolean hascustomerwithemail(String email) {
	
	return userRepo.findByEmail(email).isPresent();
}
	
}
