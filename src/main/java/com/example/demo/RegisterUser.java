package com.example.demo;

import lombok.Getter;
import lombok.Setter;

public class RegisterUser {
	@Getter @Setter private String firstName;
	@Getter @Setter private String lastName;
	@Getter @Setter private String password;
	@Getter @Setter private String email;
	@Getter @Setter private int age;
	@Getter @Setter private String phone;
	@Getter @Setter private String city;
	
	public RegisterUser() {
		firstName = lastName = password = email = phone = city = null;
		age = -1;
	}
	
	@Override
	public String toString() {
		return "RegisterUser [firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + ", email="
				+ email + ", age=" + age + ", phone=" + phone + ", city=" + city + "]";
	}
}
