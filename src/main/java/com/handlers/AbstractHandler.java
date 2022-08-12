package com.handlers;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.HotelAPIError;

public abstract class AbstractHandler<T, RT> {

	protected List<String> errors = new ArrayList<String>();

	protected T requestBody;
	protected RT responseBody;

	public abstract void init(T request, RT response);

	protected abstract void pre();

	protected abstract void actual();

	protected abstract void post();

	public final Object execute() throws Exception {
		pre();
		if (errors.size() == 0) {
			actual();
			if (errors.size() == 0) {
				post();
			} else {
				HotelAPIError err = new HotelAPIError();
				err.setStatus(false, 400);
				err.setMessage(errors.get(0));
				return (Object) err;
			}
		} else {
			HotelAPIError err = new HotelAPIError();
			err.setStatus(false, 400);
			err.setMessage(errors.get(0));
			return (Object) err;
		}
		return responseBody;
	}
}
