package com.example.demo.service;

import com.example.demo.dto.SignupRequest;
import com.example.demo.dto.UserDTO;

public interface AuthService {
  UserDTO createcustomer(SignupRequest signupRequest);
  boolean hascustomerwithemail(String email);
}
