package com.handlers;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.annotations.Required;
import com.enumm.HttpMethod;
import com.example.demo.ConsumeRest;
import com.models.DetailAPIResponse;
import com.models.Id;

import lombok.Getter;
import lombok.Setter;

public class DetailAPIHandler extends AbstractHandler<Id, DetailAPIResponse> {

	@Getter @Setter private String bearerToken = "";
	
	@Override
	public void init(Id request, DetailAPIResponse response) {
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
		String url = "https://qa.technogramsolutions.com/hms/v1/hotelDetail-search";
		ConsumeRest crest = new ConsumeRest();
		Map<String,String> headers = new HashMap<String,String>();
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
