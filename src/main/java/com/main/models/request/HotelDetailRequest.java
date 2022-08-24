package com.main.models.request;

import com.main.annotations.Required;

import lombok.Data;

@Data
public class HotelDetailRequest {
	@Required
	private String id;
}