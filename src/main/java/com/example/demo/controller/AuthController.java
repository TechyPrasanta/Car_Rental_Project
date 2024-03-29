package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SignupRequest;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
@Autowired
AuthService authService;
@PostMapping("/signup")
public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest){
	if(authService.hascustomerwithemail(signupRequest.getEmail()))
		return new ResponseEntity<>("Customer email already exists",HttpStatus.NOT_ACCEPTABLE);
	UserDTO createcustomerdto = authService.createcustomer(signupRequest);
	if(createcustomerdto == null)return new ResponseEntity<>("Customer not created, come again letter ", HttpStatus.BAD_REQUEST);
	return new ResponseEntity<>(createcustomerdto,HttpStatus.CREATED);
	
	
}



}
