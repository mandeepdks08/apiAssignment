package com.main.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.google.gson.Gson;
import com.main.enumm.HttpMethod;

import lombok.Getter;
import lombok.Setter;

public class ConsumeRest {

	private Gson gson = new Gson();
	@Setter
	private Map<?, ?> requestHeaders = null;
	@Getter
	private Map<?, ?> responseHeaders = null;
	@Getter
	private int statusCode = -1;

	private String handleError(HttpURLConnection connection) {
		StringBuilder response = new StringBuilder("");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response.toString();
	}

	private String getResponse(HttpURLConnection connection) {
		StringBuilder response = new StringBuilder("");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response.toString();
	}

	private String appendQueryParams(String url, String queryParams) {
		StringBuilder urll = new StringBuilder(url);
		if (queryParams != null) {
			urll.append("?");
			Map<?, ?> params = gson.fromJson(queryParams, Map.class);
			int i = params.size();
			for (Map.Entry<?, ?> entry : params.entrySet()) {
				urll.append(entry.getKey() + "=" + entry.getValue() + ((i == 1) ? "" : "&"));
				i--;
			}
		}
		return urll.toString();
	}

	@SuppressWarnings("unchecked")
	private String handleRequest(String urll, HttpMethod method, String body) {
		HttpURLConnection connection;
		String response = "Something went wrong!";

		// Appending query parameters to the url if present
		if (method == HttpMethod.GET) {
			urll = appendQueryParams(urll, body);
		}

		try {
			URL url = new URL(urll);
			connection = (HttpURLConnection) url.openConnection();
			if (method == HttpMethod.PATCH) {
				connection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
				connection.setRequestMethod("POST");
			} else {
				connection.setRequestMethod(method.toString());
			}

			if (requestHeaders != null) {
				for (Map.Entry<?, ?> entry : requestHeaders.entrySet()) {
					connection.setRequestProperty(entry.getKey().toString(), entry.getValue().toString());
				}
			} else {
				connection.setRequestProperty("Content-type", "application/json");
				connection.setRequestProperty("Accept", "application/json");
			}

			// Writing request body to output stream
			if (body != null && (method != HttpMethod.GET || method != HttpMethod.DELETE)) {
				connection.setDoOutput(true);
				try (OutputStream os = connection.getOutputStream()) {
					byte[] input = body.getBytes("utf-8");
					os.write(input, 0, input.length);
				}
			}

			statusCode = connection.getResponseCode();

			// Fetching response headers
			responseHeaders = connection.getHeaderFields();

			// Fetching response
			if (statusCode > 299)
				response = handleError(connection);
			else
				response = getResponse(connection);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	private <M> M parseToDataModel(String response, Class<M> model) {
//		System.out.println("In parseToDataModel");
		M obj = gson.fromJson(response, model);
//		System.out.println("Exiting parseToDataModel");
		return obj;
	}

	public <T, M> M sendRequest(String urll, HttpMethod method, T body, Class<M> responseModel) {
		String requestBody = null;
		if (body != null) {
			requestBody = gson.toJson(body);
		}
//		System.out.println(requestBody);
		String response = handleRequest(urll, method, requestBody);
//		System.out.println(response);
		M responseObj = parseToDataModel(response, responseModel);
		return responseObj;
	}
}
