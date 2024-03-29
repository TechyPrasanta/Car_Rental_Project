package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Data
@Table(name="user")
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

private Integer id;
private String name;
private String email;
private String password;
private UserRole userRole;
	
	
	
	
	
	
	
	
}
