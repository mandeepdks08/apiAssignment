package com.main.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class HotelBookingResponse {
	private String bookingId;
	private Status status;
	private Object errors;
}
