package com.main.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.main.models.request.SearchQueryRequest.SearchQuery.SearchCriteria;

public class SearchCriteriaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return SearchCriteria.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		SearchCriteria searchCriteria = (SearchCriteria) target;
		if(searchCriteria.getCity() == null)
			errors.rejectValue("city", "1009", "city is required!");
		if(searchCriteria.getNationality() == null)
			errors.rejectValue("nationality", "1010", "nationality is required!");
		if(searchCriteria.getCurrency() == null)
			errors.rejectValue("currency", "1011", "currency is required!");
	}
	
}
