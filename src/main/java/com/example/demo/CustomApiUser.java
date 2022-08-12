package com.example.demo;

import com.annotations.Mask;

import lombok.Getter;
import lombok.Setter;

public class CustomApiUser {
	@Getter @Setter private String name;
	@Getter @Setter private int age;
	
	@Mask
	@Getter @Setter private String email;
	
	@Mask
	@Getter @Setter private String phone;
	
	@Getter @Setter private Address address;
	
	private class Address {
		@Getter @Setter private String city;
		@Getter @Setter private String state;
	}
}
