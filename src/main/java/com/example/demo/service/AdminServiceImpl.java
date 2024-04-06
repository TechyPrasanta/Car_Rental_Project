package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	try {
		
	Car car = new Car();
	car.setName(carDTO.getName());
	car.setBrand(carDTO.getBrand());
	car.setColor(carDTO.getColor());
	car.setPrice(carDTO.getPrice());
	car.setYear(carDTO.getYear());
	car.setType(carDTO.getType());
	car.setDescription(carDTO.getDescription());
	car.setTransmission(carDTO.getTransmission());;
	car.setImage(carDTO.getImage().getBytes());
	carRepo.save(car);
	return true;
	}catch(Exception e) {
		
		return false;
	}
	
}

@Override
public List<CarDTO> getAllcars() {
	
	return carRepo.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
}

@Override
public void deletecar(Long id) {
	carRepo.deleteById(id);
	
}

@Override
public CarDTO getCarById(Long Id) {
	Optional<Car> optionalcar = carRepo.findById(Id);
	return optionalcar.map(Car::getCarDto).orElse(null);
}

@Override
public boolean updateCar(Long id, CarDTO carDto) throws IOException {
	Optional<Car>optionalcar = carRepo.findById(id);
	if(optionalcar.isPresent()) {
		Car existcar = optionalcar.get();
		if(carDto.getImage() != null)
		existcar.setImage(carDto.getImage().getBytes());
		existcar.setPrice(carDto.getPrice());
		existcar.setYear(carDto.getYear());
		existcar.setType(carDto.getType());
		existcar.setDescription(carDto.getDescription());
		existcar.setTransmission(carDto.getTransmission());
		existcar.setBrand(carDto.getBrand());
		existcar.setColor(carDto.getColor());
		existcar.setName(carDto.getName());
		carRepo.save(existcar);
		return true;
	}else {
		
	return false;
	}
}








}
