package com.handlers;

import java.lang.reflect.Field;

import com.annotations.MaskValue;
import com.enumm.HttpMethod;
import com.example.demo.ConsumeRest;
import com.models.LoginUser;
import com.models.LoginUserResponse;

public class UserHandlerLogin extends AbstractHandler<LoginUser, LoginUserResponse> {

	@Override
	public void init(LoginUser request, LoginUserResponse response) {
		// TODO Auto-generated method stub
		this.requestBody = request;
		this.responseBody = response;
	}

	@Override
	protected void pre() {
		// TODO Auto-generated method stub
		Field[] fields = requestBody.getClass().getDeclaredFields();
		for(Field f : fields) {
			String name = f.getName();
			switch(name) {
			case "email" : 
				try {
					if(!f.getType().getSimpleName().equals("String"))
						errors.add("Email should be of type String");
					else if(f.get(requestBody) == null)
						errors.add("Email cannot be null");
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "password": 
				try {
					if(!f.getType().getSimpleName().equals("String"))
						errors.add("Password should be of type String");
					else if(f.get(requestBody) == null)
						errors.add("Password cannot be null");
				} catch(Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

	@Override
	protected void actual() {
		// TODO Auto-generated method stub
		ConsumeRest crest = new ConsumeRest();
		responseBody = crest.sendRequest("http://localhost:3000/login", HttpMethod.POST, requestBody, responseBody.getClass());
	}

	@Override
	protected void post() {
		// TODO Auto-generated method stub
		try {
			MaskValue.maskValue(responseBody);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
