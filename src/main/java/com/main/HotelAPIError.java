package com.main;

import lombok.Data;

@Data
public class HotelAPIError {
	private Status status;
	private String message;
	
	public void setStatus(boolean success, int httpStatus) {
		status = new Status();
		status.success = success;
		status.httpStatus = httpStatus;
	}
	
	@Data
	class Status {
		private boolean success;
		private int httpStatus;
	}
}
