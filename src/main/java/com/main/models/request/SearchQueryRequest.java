package com.main.models.request;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.main.annotations.Required;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@JsonInclude(Include.NON_NULL)
public class SearchQueryRequest {
	@Required
	private SearchQuery searchQuery;
	@Required
	private Boolean sync;
	private Integer sourceId;

	@Data
	@JsonInclude(Include.NON_NULL)
	static public class SearchQuery {
		@Required
		private String checkinDate;
		@Required
		private String checkoutDate;
		@Required
		private ArrayList<RoomInfo> roomInfo;
		@Required
		private SearchCriteria searchCriteria;
		private SearchPreferences searchPreferences;

		@Data
		@JsonInclude(Include.NON_NULL)
		static public class RoomInfo {
			@Required
			private Integer numberOfAdults;
			private Integer numberOfChild;
			private ArrayList<Integer> childAge;
		}

		@Data
		@JsonInclude(Include.NON_NULL)
		static public class SearchCriteria {
			@Required
			private String city;
			@Required
			private String nationality;
			@Required
			private String currency;
		}

		@Data
		@JsonInclude(Include.NON_NULL)
		static public class SearchPreferences {
			private ArrayList<Integer> ratings;
			private String fsc;
		}

	}
}
