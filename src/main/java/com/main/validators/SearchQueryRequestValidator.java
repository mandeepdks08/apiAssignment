package com.main.validators;

import java.lang.reflect.Field;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.main.annotations.Required;
import com.main.models.request.SearchQueryRequest;
import com.main.models.request.SearchQueryRequest.SearchQuery;

public class SearchQueryRequestValidator implements Validator {

	private final Validator searchQueryValidator;

	public SearchQueryRequestValidator(Validator searchQueryValidator) {
		if (searchQueryValidator == null)
			throw new IllegalArgumentException("The supplied [Validator] is required and must not be null.");
		if (!searchQueryValidator.supports(SearchQuery.class))
			throw new IllegalArgumentException(
					"The supplied [Validator] must support the validation of [SearchQuery] instances.");
		this.searchQueryValidator = searchQueryValidator;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return SearchQueryRequest.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		SearchQueryRequest req = (SearchQueryRequest) target;
		try {
			errors.pushNestedPath("searchQuery");
			ValidationUtils.invokeValidator(this.searchQueryValidator, req.getSearchQuery(), errors);
		} finally {
			errors.popNestedPath();
		}
	}

}
