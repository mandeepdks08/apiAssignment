package com.main.validators;

import java.util.ArrayList;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.main.models.request.SearchQueryRequest.SearchQuery;
import com.main.models.request.SearchQueryRequest.SearchQuery.RoomInfo;
import com.main.models.request.SearchQueryRequest.SearchQuery.SearchCriteria;
import com.main.models.request.SearchQueryRequest.SearchQuery.SearchPreferences;

public class SearchQueryValidator implements Validator {

	private final Validator roomInfoValidator;
	private final Validator searchCriteriaValidator;
	private final Validator searchPreferencesValidator;

	public SearchQueryValidator(Validator roomInfoValidator, Validator searchCriteriaValidator,
			Validator searchPreferencesValidator) {
		if (roomInfoValidator == null)
			throw new IllegalArgumentException("The supplied [Validator] is required and must not be null.");
		if (!roomInfoValidator.supports(RoomInfo.class))
			throw new IllegalArgumentException(
					"The supplied [Validator] must support the validation of [RoomInfo] instances.");
		this.roomInfoValidator = roomInfoValidator;

		if (searchCriteriaValidator == null)
			throw new IllegalArgumentException("The supplied [Validator] is required and must not be null.");
		if (!searchCriteriaValidator.supports(SearchCriteria.class))
			throw new IllegalArgumentException(
					"The supplied [Validator] must support the validation of [SearchCriteria] instances.");
		this.searchCriteriaValidator = searchCriteriaValidator;

		if (searchPreferencesValidator == null)
			throw new IllegalArgumentException("The supplied [Validator] is required and must not be null.");
		if (!searchPreferencesValidator.supports(SearchPreferences.class))
			throw new IllegalArgumentException(
					"The supplied [Validator] must support the validation of [SearchPrefences] instances.");
		this.searchPreferencesValidator = searchPreferencesValidator;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return SearchQuery.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		SearchQuery searchQuery = (SearchQuery) target;

		// validate checkin and checkout date
		if (searchQuery.getCheckinDate() == null)
			errors.rejectValue("checkinDate", "1002", "checkinDate is required!");
		if (searchQuery.getCheckoutDate() == null)
			errors.rejectValue("checkoutDate", "1003", "checkoutDate is required!");

		// validate room info
		ArrayList<RoomInfo> roomInfo = searchQuery.getRoomInfo();
		if (roomInfo == null)
			errors.rejectValue("roomInfo", "1004", "roomInfo is required!");
		else if (roomInfo.size() == 0)
			errors.rejectValue("roomInfo", "3000", "roomInfo.size should be greater than zero!");
		else {
			for (int i = 0; i < roomInfo.size(); i++) {
				try {
					errors.pushNestedPath("roomInfo[" + i + "]");
					ValidationUtils.invokeValidator(this.roomInfoValidator, roomInfo.get(i), errors);
				} finally {
					errors.popNestedPath();
				}
			}
		}

		// validate searchCriteria
		SearchCriteria searchCriteria = searchQuery.getSearchCriteria();
		if (searchCriteria == null)
			errors.rejectValue("searchCriteria", "1005", "searchCriteria is required!");
		else {
			try {
				errors.pushNestedPath("searchCriteria");
				ValidationUtils.invokeValidator(this.searchCriteriaValidator, searchCriteria, errors);
			} finally {
				errors.popNestedPath();
			}
		}

		// validate searchPreferences
		SearchPreferences searchPreferences = searchQuery.getSearchPreferences();
		if (searchPreferences != null) {
			try {
				errors.pushNestedPath("searchPreferences");
				ValidationUtils.invokeValidator(this.searchPreferencesValidator, searchPreferences, errors);
			} finally {
				errors.popNestedPath();
			}
		}
	}
}
