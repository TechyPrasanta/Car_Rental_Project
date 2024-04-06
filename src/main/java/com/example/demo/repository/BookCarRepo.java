package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BookCar;

@Repository
public interface BookCarRepo extends JpaRepository<BookCar,Long> {

}
