package com.main.handlers;

import java.lang.reflect.Field;
import java.util.HashMap;

import com.main.annotations.Required;
import com.main.enumm.HttpMethod;
import com.main.models.request.HotelReviewRequest;
import com.main.models.response.HotelReviewResponse;
import com.main.utils.ConsumeRest;

import lombok.Getter;
import lombok.Setter;

public class HotelReviewHandler extends AbstractHandler<HotelReviewRequest, HotelReviewResponse> {

	@Getter @Setter private String bearerToken = "";
	
	@Override
	public void init(HotelReviewRequest request, HotelReviewResponse response) {
		// TODO Auto-generated method stub
		this.requestBody = request;
		this.responseBody = response;
	}

	@Override
	protected void pre() {
		// TODO Auto-generated method stub
		Field[] fields = requestBody.getClass().getDeclaredFields();
		for(Field f: fields) {
			f.setAccessible(true);
			try {
				if(f.isAnnotationPresent(Required.class) && f.get(requestBody) == null) {
					errors.add(f.getName() + " is required.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void actual() {
		// TODO Auto-generated method stub
		String url = "https://qa.technogramsolutions.com/hms/v1/hotel-review";
		ConsumeRest crest = new ConsumeRest();
		HashMap<String,String> headers = new HashMap<String,String>();
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
