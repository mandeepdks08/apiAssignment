package com.main.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class UserDetails {
//	@NotBlank(message = "Please enter your username!")
	private String username;
//	@NotBlank(message = "Please enter your password")
	private String password;
}
