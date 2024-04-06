package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.BookCarDTO;
import com.example.demo.entity.BookCar;

@Repository
public interface BookCarRepo extends JpaRepository<BookCar,Long> {

	List<BookCarDTO> findAllByUserId(Long userId);

}
