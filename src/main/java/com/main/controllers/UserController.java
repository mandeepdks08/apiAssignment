package com.main.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.main.RegisterUser;
import com.main.enumm.HttpMethod;
import com.main.exceptions.GeneralException;
import com.main.handlers.CancellationPolicyHandler;
import com.main.handlers.HotelBookingHandler;
import com.main.handlers.HotelDetailHandler;
import com.main.handlers.HotelReviewHandler;
import com.main.handlers.SearchHotelHandler;
import com.main.handlers.SearchQueryHandler;
import com.main.models.SearchId;
import com.main.models.TestPrimitiveTypes;
import com.main.models.UserDetails;
import com.main.models.UserResponse;
import com.main.models.request.CancellationPolicyRequest;
import com.main.models.request.HotelBookingRequest;
import com.main.models.request.HotelDetailRequest;
import com.main.models.request.HotelReviewRequest;
import com.main.models.request.SearchQueryRequest;
import com.main.models.response.CancellationPolicyResponse;
import com.main.models.response.HotelBookingResponse;
import com.main.models.response.HotelDetailResponse;
import com.main.models.response.HotelReviewResponse;
import com.main.models.response.SearchHotelResponse;
import com.main.models.response.SearchQueryResponse;
import com.main.utils.ConsumeRest;
import com.main.validators.RoomInfoValidator;
import com.main.validators.SearchCriteriaValidator;
import com.main.validators.SearchPreferencesValidator;
import com.main.validators.SearchQueryRequestValidator;
import com.main.validators.SearchQueryValidator;
import com.main.validators.UserDetailsValidator;

@RestController
public class UserController {

	@Autowired
	UserDetailsValidator userDetailsValidator;

//	@InitBinder(value = "user")
//	public void initBinderUserDetails(WebDataBinder binder) {
//		System.out.println("In initBinder");
//		binder.addValidators(userDetailsValidator);
//	}
	
//	@InitBinder
//	public void initBinderSearchQueryRequest(WebDataBinder binder) {
//		SearchQueryRequestValidator searchQueryRequestValidator = new SearchQueryRequestValidator(new SearchQueryValidator(new RoomInfoValidator(), new SearchCriteriaValidator(), new SearchPreferencesValidator()));
//		binder.addValidators(searchQueryRequestValidator);
//	}
	

	@PostMapping("/sign-in")
	public UserResponse signIn(@RequestBody UserDetails user, BindingResult result)
			throws MethodArgumentNotValidException {
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		System.out.println(user);
		String url = "https://qa.technogramsolutions.com/ums/v1/sign-in";
		ConsumeRest crest = new ConsumeRest();
		UserResponse res = crest.sendRequest(url, HttpMethod.POST, user, UserResponse.class);
		return res;
	}

	// 5.1
	@PostMapping("/hotel-searchquery-list")
	public Object getSearchIds(@Valid @RequestBody SearchQueryRequest req, @RequestHeader Map<String, String> headers, BindingResult result) throws MethodArgumentNotValidException {
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
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

	// 5.2
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

	// 5.3
	@PostMapping("/hotelDetail-search")
	public Object hotelDetail(@RequestBody HotelDetailRequest req, @RequestHeader Map<String, String> headers) {
		HotelDetailHandler handler = new HotelDetailHandler();
		String token = headers.get("authorization");
		handler.init(req, new HotelDetailResponse());
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

	// 5.4
	@PostMapping("/hotel-cancellation-policy")
	public Object hotelCancellationPolicy(@RequestBody CancellationPolicyRequest req,
			@RequestHeader Map<String, String> headers) {
		CancellationPolicyHandler handler = new CancellationPolicyHandler();
		String token = headers.get("authorization");
		handler.init(req, new CancellationPolicyResponse());
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

	// 5.5
	@PostMapping("/hotel-review")
	public Object hotelReview(@RequestBody HotelReviewRequest req, @RequestHeader Map<String, String> headers) {
		String token = headers.get("authorization");
		HotelReviewHandler handler = new HotelReviewHandler();
		handler.init(req, new HotelReviewResponse());
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
	
	// 5.6
	@PostMapping("/hotel-booking")
	public Object hotelBooking(@RequestBody HotelBookingRequest req, @RequestHeader Map<String,String> headers) {
		System.out.println(req);
		String token = headers.get("authorization");
		HotelBookingHandler handler = new HotelBookingHandler();
		handler.setBearerToken(token);
		handler.init(req, new HotelBookingResponse());
		Object res = null;
		try {
			res = handler.execute();
		} catch(Exception e) {
			res = e;
			e.printStackTrace();
		}
		return res;
	}
	
	@PostMapping("/test-primitive-types")
	public Object testPrimitiveTypes(@RequestBody TestPrimitiveTypes req) {
		System.out.println(req.getNum());
		System.out.println(req.getCh());
		return req;
	}

}
