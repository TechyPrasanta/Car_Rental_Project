package com.example.demo.dto;

import java.util.Date;

import com.example.demo.entity.BookcarStatus;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BookCarDTO {

	private Long id;	
	private Date fromdate;
	private Date todate;
	private Long days;
	private Long price;
	
	private BookcarStatus bookcarStatus;
	
	private Long carId;
	
	private Long userId;
}
