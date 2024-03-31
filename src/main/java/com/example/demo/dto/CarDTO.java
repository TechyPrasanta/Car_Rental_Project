package com.example.demo.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CarDTO {

	private Integer id;
	private String brand;
	private String color;
	private String name;
	private String type;
	private String transmission;
	private String description;
	private Long price;
	private Date year;
	private byte[]image;
    private MultipartFile returnedimage;
}
