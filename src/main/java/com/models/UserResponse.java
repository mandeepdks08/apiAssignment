package com.models;

import lombok.Getter;
import lombok.Setter;

public class UserResponse {
	@Getter @Setter private String accessToken;

	public UserResponse(String accessToken) {
		this.accessToken = accessToken;
	}
}
