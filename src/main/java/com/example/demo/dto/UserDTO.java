package com.example.demo.dto;

import com.example.demo.entity.UserRole;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Data
public class UserDTO {

private Integer id;
private String name;
private String email;
private UserRole userRole;

}
