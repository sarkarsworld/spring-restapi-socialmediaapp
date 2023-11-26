package com.simplesolutions.versioning;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Name {
	
	private String firstName;
	private String lastName;
	
	public Name(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
