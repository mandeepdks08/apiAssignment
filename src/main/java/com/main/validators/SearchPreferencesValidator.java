package com.main.validators;

import java.util.ArrayList;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.main.models.request.SearchQueryRequest.SearchQuery.SearchPreferences;

public class SearchPreferencesValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return SearchPreferences.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		SearchPreferences searchPreferences = (SearchPreferences) target;
		ArrayList<Integer> ratings = searchPreferences.getRatings();
		if(ratings != null) {
			for(Integer rating: ratings) {
				if(rating < 1 || rating > 5) {
					errors.rejectValue("rating", "3004", "ratings should be in range [1,5]!");
					break;
				}
			}
		}
	}

}
