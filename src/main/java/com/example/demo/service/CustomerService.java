package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.BookCarDTO;
import com.example.demo.dto.CarDTO;

public interface CustomerService {
	
 List<CarDTO>getAllcars();
 
 boolean bookACar(BookCarDTO bookCarDTO);
 
 CarDTO getcarById(Long Id);
 
 List<BookCarDTO>getBookByuserId(Long userId);
 
 
 
}
