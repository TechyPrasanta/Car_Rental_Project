package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CarDTO;
import com.example.demo.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

@Autowired
AdminService adminService;

@PostMapping("/car")
public ResponseEntity<?>postCar(@ModelAttribute CarDTO carDTO){
	boolean succes = adminService.postCar(carDTO);
	if(succes) {
		return ResponseEntity.status(HttpStatus.CREATED).build();		
	}else {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    }	
  }
	
}
