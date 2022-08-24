package com.main.handlers;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.main.annotations.Required;
import com.main.enumm.HttpMethod;
import com.main.models.request.CancellationPolicyRequest;
import com.main.models.response.CancellationPolicyResponse;
import com.main.utils.ConsumeRest;

import lombok.Getter;
import lombok.Setter;

public class CancellationPolicyHandler extends AbstractHandler<CancellationPolicyRequest, CancellationPolicyResponse>{

	@Getter @Setter private String bearerToken = "";
	
	@Override
	public void init(CancellationPolicyRequest request, CancellationPolicyResponse response) {
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
					errors.add(f.getName() + " is required!");
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void actual() {
		// TODO Auto-generated method stub
		String url = "https://qa.technogramsolutions.com/hms/v1/hotel-cancellation-policy";
		ConsumeRest crest = new ConsumeRest();
		Map<String,String> headers = new HashMap<String,String>();
		headers.put("Content-type", "application/json");
		headers.put("Accept", "application/json");
		headers.put("Authorization", "Bearer " + bearerToken);
		crest.setRequestHeaders(headers);
		responseBody = crest.sendRequest(url,HttpMethod.POST, requestBody, responseBody.getClass());
	}

	@Override
	protected void post() {
		// TODO Auto-generated method stub
		
	}

}
