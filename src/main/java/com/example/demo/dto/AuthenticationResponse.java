package com.example.demo.dto;

import com.example.demo.entity.UserRole;

import lombok.Data;

@Data
public class AuthenticationResponse {

	private String jwt;
	private UserRole userRole;
	private Integer userId;
}
