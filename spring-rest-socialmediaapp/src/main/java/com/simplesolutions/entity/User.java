package com.simplesolutions.entity;

import java.time.LocalDate;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
	
	private int id;
	@Size(min = 2, message = "name should have min of 2 characters.")
	private String name;
	@Past(message = "DOB should be a past date.")
	private LocalDate dob;
	

}
