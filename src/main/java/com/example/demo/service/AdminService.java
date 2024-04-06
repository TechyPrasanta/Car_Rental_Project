package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import com.example.demo.dto.CarDTO;

public interface AdminService {

	boolean postCar(CarDTO carDTO);
	
	List<CarDTO>getAllcars();
	
	void deletecar(Long id);
	
	CarDTO getCarById(Long Id);
	
	boolean updateCar(Long id, CarDTO carDto) throws IOException;
	
}
