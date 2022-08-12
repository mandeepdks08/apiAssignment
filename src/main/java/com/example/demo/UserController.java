package com.example.demo;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.enumm.HttpMethod;
import com.handlers.DetailAPIHandler;
import com.handlers.SearchHotelHandler;
import com.handlers.SearchQueryHandler;
import com.handlers.UserHandlerLogin;
import com.handlers.UserHandlerRegister;
import com.models.DetailAPIResponse;
import com.models.Id;
import com.models.LoginUser;
import com.models.LoginUserResponse;
import com.models.RegisterUserResponse;
import com.models.SearchHotelResponse;
import com.models.SearchId;
import com.models.SearchQueryRequest;
import com.models.SearchQueryResponse;
import com.models.UserDetails;
import com.models.UserResponse;

@RestController
public class UserController {

	@PostMapping("/sign-in")
	public UserResponse signIn(@RequestBody UserDetails user) {
		String url = "https://qa.technogramsolutions.com/ums/v1/sign-in";
		ConsumeRest crest = new ConsumeRest();
		UserResponse res = crest.sendRequest(url, HttpMethod.POST, user, UserResponse.class);
		return res;
	}

	@PostMapping("/user/register")
	public /* RegisterUserResponse */ Object register(@RequestBody RegisterUser user) {
		UserHandlerRegister handlerRegister = new UserHandlerRegister();
		handlerRegister.init(user, new RegisterUserResponse());
		Object response = null;
		try {
			response = handlerRegister.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	@PostMapping("/user/login")
	public /* LoginUserResponse */ Object login(@RequestBody LoginUser user) {
		UserHandlerLogin handlerLogin = new UserHandlerLogin();
		handlerLogin.init(user, new LoginUserResponse());
		Object response = null;
		try {
			response = handlerLogin.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	@PostMapping("/hotel-searchquery-list")
	public Object getSearchIds(@RequestBody SearchQueryRequest req, @RequestHeader Map<String, String> headers) {
		String token = headers.get(new String("authorization"));
		SearchQueryHandler sqh = new SearchQueryHandler();
		sqh.init(req, new SearchQueryResponse());
		sqh.setBearerToken(token);
		Object res = null;
		try {
			res = sqh.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			res = e;
			e.printStackTrace();
		}
		return res;
	}

	@PostMapping("/hotel-search")
	public Object searchHotelById(@RequestBody SearchId req, @RequestHeader Map<String, String> headers) {
		String token = headers.get(new String("authorization"));
		SearchHotelHandler handler = new SearchHotelHandler();
		handler.init(req, new SearchHotelResponse());
		handler.setBearerToken(token);
		Object resp = null;
		try {
			resp = handler.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			resp = e;
			e.printStackTrace();
		}
		return resp;
	}
	
	@PostMapping("/hotelDetail-search")
	public Object hotelDetail(@RequestBody Id req, @RequestHeader Map<String,String> headers) {
		DetailAPIHandler handler = new DetailAPIHandler();
		String token = headers.get("authorization");
		handler.init(req, new DetailAPIResponse());
		handler.setBearerToken(token);
		Object res = null;
		try {
			res = handler.execute();
		} catch (Exception e) {
			res = e;
			e.printStackTrace();
		}
		return res;
	}

}
