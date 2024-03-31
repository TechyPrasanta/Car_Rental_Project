package com.example.demo.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

@PostConstruct
public void createAdminAccount() {
	User adminAccount = userRepo.findByUserRole(UserRole.Admin);
	if(adminAccount == null) {
		User newAdminAccount = new User();
		newAdminAccount.setName("Admin");
		newAdminAccount.setEmail("admin@test.com");
		newAdminAccount.setPassword(new BCryptPasswordEncoder().encode("admin@123"));
		newAdminAccount.setUserRole(UserRole.Admin);
		userRepo.save(newAdminAccount);
		System.out.println("Admin account created succesfully");
		
	}
}




@Override
public UserDTO createcustomer(SignupRequest signupRequest) {
	User user = new User();
	user.setName(signupRequest.getName());
	user.setEmail(signupRequest.getEmail());
	user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
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
