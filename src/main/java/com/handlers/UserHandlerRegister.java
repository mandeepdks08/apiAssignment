package com.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Component;

import com.annotations.MaskValue;
import com.enumm.HttpMethod;
import com.example.demo.ConsumeRest;
import com.example.demo.RegisterUser;
import com.google.gson.Gson;
import com.models.RegisterUserResponse;

/*
 M -> Mandatory
 O -> Optional
 Registration form
 1. First Name	(M)
 2. Last Name	(O)	
 2. Phone		(M)
 3. Email		(O)
 4. Age			(M)
 5. City		(M)
 6. Password	(M)
 */

@Component
public class UserHandlerRegister extends AbstractHandler<RegisterUser, RegisterUserResponse> {

	@Override
	public void init(RegisterUser request, RegisterUserResponse response) {
		// TODO Auto-generated method stub
		this.requestBody = request;
		this.responseBody = response;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void pre() {
		// TODO Auto-generated method stub
//		System.out.println("In preProcessing...");
		Class clazz = requestBody.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			String name = f.getName();
			switch (name) {
			case "firstName":
				try {
					if (!f.getType().getSimpleName().equals("String"))
						errors.add("First name should be of type String");
					else if (f.get(requestBody) == null)
						errors.add("First name cannot be null");
					else if (((String) f.get(requestBody)).length() == 0)
						errors.add("First name is required");
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "lastName":
				try {
					if (f.get(requestBody) != null && !f.getType().getSimpleName().equals("String"))
						errors.add("Last name should be of type String");
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "phone":
				try {
					if (!f.getType().getSimpleName().equals("String"))
						errors.add("Phone should be of type String");
					else if (f.get(requestBody) == null)
						errors.add("Phone no. is required");
					else if (((String) f.get(requestBody)).length() != 10)
						errors.add("Phone no. should contain 10 digits");
					else {
						String phone = (String) f.get(requestBody);
						for (int i = 0; i < phone.length(); i++) {
							int start = '0';
							if ((int) (phone.charAt(i)) < start || (int) (phone.charAt(i)) > start + 9) {
								errors.add("Invalid phone no.");
								break;
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "email":
				if (!f.getType().getSimpleName().equals("String"))
					errors.add("Email should be of type String");
				break;
			case "age":
				try {
					if (!f.getType().getSimpleName().equals("int"))
						errors.add("Age should be of type int");
					else {
						f.setAccessible(true);
						if (f.get(requestBody) == null)
							errors.add("Age cannot be null");
						else if ((int) f.get(requestBody) < 0)
							errors.add("Age should be greater than 0");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "city":
				try {
					if (!f.getType().getSimpleName().equals("String"))
						errors.add("City should be of type String");
					else if (f.get(requestBody) == null)
						errors.add("City cannot be null");
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "password":
				try {
					if (!f.getType().getSimpleName().equals("String"))
						errors.add("Password should be of type String");
					else if (f.get(requestBody) == null)
						errors.add("Password cannot be null");
					else if (((String) f.get(requestBody)).length() < 8)
						errors.add("Password length should be greater than or equal to 8");
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			default:

			}
		}
//		System.out.println("Exiting preProcessing...");
	}

	@Override
	protected void actual() {
		// TODO Auto-generated method stub
//		System.out.println("In acutal...");

		ConsumeRest crest = new ConsumeRest();
		responseBody = crest.sendRequest("http://localhost:3000/register", HttpMethod.POST, requestBody,
				responseBody.getClass());
//		System.out.println("Exiting actual...");
	}

	@Override
	protected void post() {
		// TODO Auto-generated method stub
//		System.out.println("In postProcessing...");
		try {
			MaskValue.maskValue(responseBody);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("Exiting postProcessing...");
	}

}

//HttpURLConnection connection = null;
//Gson gson = new Gson();
//try {
//	URL url = new URL("http://localhost:3000/users/register");
//	connection = (HttpURLConnection) url.openConnection();
//	connection.setDoOutput(true);
//	connection.setRequestMethod("POST");
//	connection.setRequestProperty("Content-type", "application/json");
//
//	try (OutputStream os = connection.getOutputStream()) {
//		byte[] input = gson.toJson(requestBody).getBytes("utf-8");
//		os.write(input, 0, input.length);
//	}
//
//	int status = connection.getResponseCode();
//	if (status > 299) {
//		System.out.println("Something went wrong!");
//		responseBody = null;
//	} else {
//		StringBuilder response = new StringBuilder("");
//		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//		String line = null;
//		while ((line = reader.readLine()) != null) {
//			response.append(line);
//		}
//		System.out.println("Response(StringBuilder)\n"+response.toString());
//		responseBody = gson.fromJson(response.toString(), responseBody.getClass());
//		System.out.println("Response(ResponseBodyObject)\n"+responseBody);
//	}
//} catch (IOException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}