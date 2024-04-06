package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthenticationRequest;
import com.example.demo.dto.AuthenticationResponse;
import com.example.demo.dto.SignupRequest;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserService;
import com.example.demo.utils.JWTutils;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
@Autowired
AuthService authService;

@Autowired
AuthenticationManager authenticationManager;

@Autowired
UserService userService;

@Autowired
JWTutils jwtUtils;

@Autowired
UserRepo userRepo;


@PostMapping("/signup")
public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest){
	if(authService.hascustomerwithemail(signupRequest.getEmail()))
		return new ResponseEntity<>("Customer email already exists",HttpStatus.NOT_ACCEPTABLE);
	UserDTO createcustomerdto = authService.createcustomer(signupRequest);
	if(createcustomerdto == null)return new ResponseEntity<>("Customer not created, come again letter ", HttpStatus.BAD_REQUEST);
	return new ResponseEntity<>(createcustomerdto,HttpStatus.CREATED);		
}


@PostMapping("/login")
public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
    try {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
    } catch (BadCredentialsException e) {
        throw new BadCredentialsException("Incorrect Username or password");
    }

    final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
    Optional<User> optionalUser = userRepo.findByEmail(authenticationRequest.getEmail());
    
    if (optionalUser.isPresent()) {
        final String jwt = jwtUtils.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setJwt(jwt);
        authenticationResponse.setUserId(optionalUser.get().getId());
        authenticationResponse.setUserRole(optionalUser.get().getUserRole());
        return ResponseEntity.ok(authenticationResponse);
    } else {
        throw new UsernameNotFoundException("User not found with email: " + authenticationRequest.getEmail());
    }
}
}



