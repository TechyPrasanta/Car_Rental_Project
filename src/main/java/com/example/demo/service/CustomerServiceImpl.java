package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BookCarDTO;
import com.example.demo.dto.CarDTO;
import com.example.demo.entity.BookCar;
import com.example.demo.entity.BookcarStatus;
import com.example.demo.entity.Car;
import com.example.demo.entity.User;
import com.example.demo.repository.BookCarRepo;
import com.example.demo.repository.CarRepo;
import com.example.demo.repository.UserRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CarRepo carRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	BookCarRepo bookCarRepo;

	@Override
	public List<CarDTO> getAllcars() {		
		return carRepo.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
	}

	@Override
	public boolean bookACar(BookCarDTO bookCarDTO) {
	Optional<Car> optionalcar = carRepo.findById(bookCarDTO.getCarId());
	Optional<User>optionaluser = userRepo.findById(bookCarDTO.getUserId());
	if(optionalcar.isPresent()&&optionaluser.isPresent()) {
		Car existingcar = optionalcar.get();
		BookCar bookCar = new BookCar();
		bookCar.setCar(existingcar);
		bookCar.setUser(optionaluser.get());
		bookCar.setBookcarStatus(BookcarStatus.PENDING);
		long diffInMilliSeconds = bookCarDTO.getTodate().getTime()-bookCarDTO.getFromdate().getTime();
		long days = TimeUnit.MICROSECONDS.toDays(diffInMilliSeconds);
		bookCar.setDays(days);
		bookCar.setPrice(existingcar.getPrice()*days);
		bookCarRepo.save(bookCar);
		return true;
	   }
	
		return false;
	}
	
	
	
}
