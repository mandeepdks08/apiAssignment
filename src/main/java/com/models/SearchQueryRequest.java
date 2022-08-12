package com.models;

import java.util.ArrayList;

import com.annotations.Required;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@JsonInclude(Include.NON_NULL)
class RoomInfo {
	@Required
	private int numberOfAdults;
	private int numberOfChild;
	private ArrayList<Integer> childAge;
}

@Data
@JsonInclude(Include.NON_NULL)
class SearchCriteria {
	@Required
	private String city;
	@Required
	private String nationality;
	@Required
	private String currency;
}

@Data
@JsonInclude(Include.NON_NULL)
class SearchPreferences {
	private ArrayList<Integer> ratings;
	private String fsc;
}

@Data
@JsonInclude(Include.NON_NULL)
class SearchQuery {
	@Required
	private String checkinDate;
	@Required
	private String checkoutDate;
	@Required
	private ArrayList<RoomInfo> roomInfo;
	@Required
	private SearchCriteria searchCriteria;
	private SearchPreferences searchPreferences;

}

@Data
@JsonInclude(Include.NON_NULL)
public class SearchQueryRequest {
	@Required
	private SearchQuery searchQuery;
	@Required
	private Boolean sync;
}
