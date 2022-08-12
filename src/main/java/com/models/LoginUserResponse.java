package com.models;

import lombok.Getter;
import lombok.Setter;

public class LoginUserResponse {
	@Getter @Setter private String message;
	@Getter @Setter private int status;
	@Getter @Setter private String token;
}
