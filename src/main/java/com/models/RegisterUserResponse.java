package com.models;

import lombok.Getter;
import lombok.Setter;

public class RegisterUserResponse {
//	@Getter @Setter private String firstName;
//	@Getter @Setter private String lastName;
//	@Getter @Setter @Mask private String password;
//	@Getter @Setter @Mask private String email;
//	@Getter @Setter private int age;
//	@Getter @Setter @Mask private String phone;
//	@Getter @Setter private String city;
//	
//	public RegisterUserResponse() {
//		firstName = lastName = password = email = phone = city = null;
//		age = -1;
//	}
//	
//	@Override
//	public String toString() {
//		return "RegisterUserResponse [firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
//				+ ", email=" + email + ", age=" + age + ", phone=" + phone + ", city=" + city + "]";
//	}
	
	@Getter @Setter private String message;
	@Getter @Setter private int status;
	
	public RegisterUserResponse() {
		message = "DEFAULT";
		status = -1;
	}

	@Override
	public String toString() {
		return "RegisterUserResponse [message=" + message + ", status=" + status + "]";
	}
	
}
