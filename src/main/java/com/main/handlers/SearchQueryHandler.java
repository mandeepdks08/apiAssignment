package com.main.handlers;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;

import org.springframework.util.ClassUtils;

import com.main.annotations.Required;
import com.main.enumm.HttpMethod;
import com.main.models.request.SearchQueryRequest;
import com.main.models.response.SearchQueryResponse;
import com.main.utils.ConsumeRest;

import lombok.Getter;
import lombok.Setter;

public class SearchQueryHandler extends AbstractHandler<SearchQueryRequest, SearchQueryResponse> {

	@Getter
	@Setter
	private String bearerToken = "";

	@Override
	public void init(SearchQueryRequest request, SearchQueryResponse response) {
		// TODO Auto-generated method stub
		this.requestBody = request;
		this.responseBody = response;
	}

	private <T> void preHelper(T obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			try {
				if (f.isAnnotationPresent(Required.class) && f.get(obj) == null) {
					errors.add(f.getName() + " is required");
					break;
				} else if (!ClassUtils.isPrimitiveOrWrapper(f.getType())) {
					preHelper(f.get(obj));
					if(errors.size() > 0) break;
				} 
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private int compareDates(Date d1, Date d2) {
		
		return 0;
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
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("content-type", "application/json");
		headers.put("accept", "application/json");
		headers.put("Authorization", "Bearer " + bearerToken);
		crest.setRequestHeaders(headers);
		String url = "https://qa.technogramsolutions.com/hms/v1/hotel-searchquery-list";
		responseBody = crest.sendRequest(url, HttpMethod.POST, requestBody, responseBody.getClass());
	}

	@Override
	protected void post() {
		// TODO Auto-generated method stub

	}

}
