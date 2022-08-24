package com.main.handlers;

import java.util.HashMap;

import com.main.enumm.HttpMethod;
import com.main.models.request.HotelBookingRequest;
import com.main.models.response.HotelBookingResponse;
import com.main.utils.ConsumeRest;

import lombok.Getter;
import lombok.Setter;

public class HotelBookingHandler extends AbstractHandler<HotelBookingRequest, HotelBookingResponse>{

	@Getter @Setter
	private String bearerToken = "";
	
	@Override
	public void init(HotelBookingRequest request, HotelBookingResponse response) {
		// TODO Auto-generated method stub
		this.requestBody = request;
		this.responseBody = response;
	}

	@Override
	protected void pre() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void actual() {
		// TODO Auto-generated method stub
		String url = "https://qa.technogramsolutions.com/oms/v1/hotel/book";
		ConsumeRest crest = new ConsumeRest();
		HashMap<String,String> headers = new HashMap();
		headers.put("Content-type", "application/json");
		headers.put("Accept", "application/json");
		headers.put("Authorization", "Bearer " + bearerToken);
		crest.setRequestHeaders(headers);
		responseBody = crest.sendRequest(url, HttpMethod.POST, requestBody, responseBody.getClass());
	}

	@Override
	protected void post() {
		// TODO Auto-generated method stub
		
	}

}
