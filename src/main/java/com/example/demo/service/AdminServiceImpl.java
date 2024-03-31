package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CarDTO;
import com.example.demo.entity.Car;
import com.example.demo.repository.CarRepo;

@Service
public class AdminServiceImpl implements AdminService{

@Autowired
CarRepo carRepo;

@Override
public boolean postCar(CarDTO carDTO) {
	Car car = new Car();
	car.setName(carDTO.getName());
	car.setBrand(carDTO.getBrand());
	car.setColor(carDTO.getColor());
	return false;
}








}
