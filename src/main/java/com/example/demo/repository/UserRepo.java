package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
@Repository
public interface UserRepo extends JpaRepository<User,Long>,CrudRepository<User,Long> {

	Optional<User> findByEmail(String email);

	User findByUserRole(UserRole userRole);

}
