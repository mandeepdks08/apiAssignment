package com.main.handlers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.util.ClassUtils;

import com.main.annotations.Required;
import com.main.enumm.HttpMethod;
import com.main.models.SearchId;
import com.main.models.response.SearchHotelResponse;
import com.main.utils.ConsumeRest;

import lombok.Getter;
import lombok.Setter;

public class SearchHotelHandler extends AbstractHandler<SearchId, SearchHotelResponse> {

	@Getter @Setter private String bearerToken = "";
	
	@Override
	public void init(SearchId request, SearchHotelResponse response) {
		// TODO Auto-generated method stub
		this.requestBody= request;
		this.responseBody = response;
	}

	private <T> void preHelper(T obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field f: fields) {
			f.setAccessible(true);
			try {
				if(f.isAnnotationPresent(Required.class) && f.get(obj) == null) {
					if(f.get(obj) == null)
						errors.add(f.getName() + " is required.");
					else if(f.getType() == ArrayList.class && ((List<String>) f.get(obj)).size() == 0)
					errors.add(f.getName() + " is required.");
					break;
				} else if(!ClassUtils.isPrimitiveOrWrapper(f.getType())) {
					if(f.getType() == ArrayList.class) {
						
						ArrayList<?> list = (ArrayList<?>) f.get(obj);
						for(int i=0;i<list.size();i++) {
							preHelper(list.get(i));
						}
					} else {
						preHelper(f.get(obj));
					}
					if(errors.size() > 0) break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void pre() {
		// TODO Auto-generated method stub
		preHelper(requestBody);
	}

	@Override
	protected void actual() {
		// TODO Auto-generated method stub
		ConsumeRest crest = new ConsumeRest();
		String url = "https://qa.technogramsolutions.com/hms/v1/hotel-search";
		HashMap<String,String> headers = new HashMap<String,String>();
		headers.put("content-type", "application/json");
		headers.put("accept", "application/json");
		headers.put("authorization", "Bearer "+bearerToken);
		crest.setRequestHeaders(headers);
		responseBody = crest.sendRequest(url, HttpMethod.POST, requestBody, responseBody.getClass());
		Object obj = crest.sendRequest(url, HttpMethod.POST, requestBody, Object.class);
		System.out.println(obj);
	}

	@Override
	protected void post() {
		// TODO Auto-generated method stub
		
	}


}
