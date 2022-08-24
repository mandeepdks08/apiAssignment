package com.main.models.request;

import com.main.annotations.Required;

import lombok.Data;

@Data
public class HotelReviewRequest {
	@Required
	private String hotelId;
	@Required
	private String optionId;
}
