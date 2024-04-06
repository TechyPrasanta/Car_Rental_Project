package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CarDTO;
import com.example.demo.service.AdminService;

import io.jsonwebtoken.io.IOException;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
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

@GetMapping("/cars")
public ResponseEntity<?>getAllCars(){
	     return ResponseEntity.ok(adminService.getAllcars());
}

@DeleteMapping("/car{id}")
public ResponseEntity<Void>deleteCar(@PathVariable Long id){
	adminService.deletecar(id);
	return ResponseEntity.ok(null);
 }

@GetMapping("/car{id}")
public ResponseEntity<CarDTO>getCarById(@PathVariable Long id){
	CarDTO carDto = adminService.getCarById(id);
	return ResponseEntity.ok(carDto);
}

@PutMapping("/car/{id}") // Corrected path variable syntax
public ResponseEntity<Void> updateCar(@PathVariable Long id, @ModelAttribute CarDTO carDto) {
    try {
        boolean success = adminService.updateCar(id, carDto);
        if (success) {
            return ResponseEntity.ok().build(); // Return 200 OK if update is successful
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if car with given ID is not found
        }
    } catch (Exception e) {
        return ResponseEntity.badRequest().build(); // Return 400 Bad Request in case of any other error
    }
}




}
