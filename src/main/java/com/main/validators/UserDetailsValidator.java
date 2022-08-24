package com.main.validators;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.main.models.UserDetails;

@Service
public class UserDetailsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserDetails.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserDetails user = (UserDetails) target;
		if(usernameNullOrEmpty(user.getUsername())) {
			errors.rejectValue("username", "1000", "Please provide your username!");
		}
		
		if(passwordNullOrEmpty(user.getPassword())) {
			errors.rejectValue("password", "1001", "Please provide your password!");
		}
	}
	
	private boolean usernameNullOrEmpty(String username) {
		return (username == null || username.trim().length() == 0);
	}
	
	private boolean passwordNullOrEmpty(String password) {
		return password == null || password.trim().length() == 0;
	}

}
