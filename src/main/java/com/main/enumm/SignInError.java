package com.main.enumm;

public enum SignInError {
	USERNAME_NULL(1000),
	PASSWORD_NULL(1001);
	
	private final int value;

	SignInError(final int newValue) {
		value = newValue;
	}

	public int getValue() {
		return value;
	}
}
