package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BookCarDTO;
import com.example.demo.dto.CarDTO;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

@Autowired 
CustomerService customerService;
	
@GetMapping("/cars")	
public ResponseEntity<List<CarDTO>>	getAllCars(){
	List<CarDTO> carDtoList = customerService.getAllcars();
	return ResponseEntity.ok(carDtoList);
}

@PostMapping("/car/book")
public ResponseEntity<Void>bookcar(@RequestBody BookCarDTO bookcarDto){
	boolean succes = customerService.bookACar(bookcarDto);
	if(succes)
		return ResponseEntity.status(HttpStatus.CREATED).build();
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
}
	
}
