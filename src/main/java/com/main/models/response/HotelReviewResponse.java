package com.main.models.response;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class HotelReviewResponse {
	private Object hInfo;
	private String bookingId;
	private ArrayList<Object> alerts;
	private Object query;
	private boolean isPriceChanged;
	private Object status;
	private Object conditions;
}
