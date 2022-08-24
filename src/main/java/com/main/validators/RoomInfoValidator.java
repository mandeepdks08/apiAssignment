package com.main.validators;

import java.util.ArrayList;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.main.models.request.SearchQueryRequest.SearchQuery.RoomInfo;

public class RoomInfoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return RoomInfo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		RoomInfo roomInfo = (RoomInfo) target;
		Integer numberOfAdults = roomInfo.getNumberOfAdults();
		if(numberOfAdults == null)
			errors.rejectValue("numberOfAdults", "1006", "numberOfAdults is required!");
		else if(numberOfAdults  <= 0)
			errors.rejectValue("numberOfAdults", "3001", "numberOfAdults should be greater than zero!");
		
		Integer numberOfChild = roomInfo.getNumberOfChild();
		if(numberOfChild != null) {
			if(numberOfChild < 0)
				errors.rejectValue("numberOfChild", "3002", "numberOfChild should be greater than or equal to zero!");
			else if(numberOfChild != 0) {
				if(roomInfo.getChildAge() == null)
					errors.rejectValue("childAge", "1007", "childAge is required as numberOfChild is greater than zero!");
				else {
					ArrayList<Integer> childAge = roomInfo.getChildAge();
					if(childAge.size() != numberOfChild)
						errors.rejectValue("childAge", "1008", "childAge.size should be equal to numberOfChild!");
					else {
						for(int i=0;i<childAge.size();i++)
							if(childAge.get(i) < 0)
								errors.rejectValue("childAge", "3003", "childAge["+i+"] should be greater than or equal to zero!");
					}
				}
			}
		}
	}

}
