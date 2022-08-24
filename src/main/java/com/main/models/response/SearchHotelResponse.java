package com.main.models.response;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class SearchHotelResponse {
	private SearchResult searchResult;
	private int retryInSecond;
	private Status status;
	private Object metaInfo;

	@JsonInclude(Include.NON_NULL)
	@Data
	class SearchResult {
		private ArrayList<HotelInfo> his;
		private int size;

		@JsonInclude(Include.NON_NULL)
		@Data
		class HotelInfo {
			private String id;
			private String name;
			private ArrayList<Image> img;
			private String des;
			private int rt;
			private GeoLocation gl;
			private Address ad;
			private String pt;
			private ArrayList<Pops> pops;
			private ArrayList<String> ss;
			private String sn;
			private String uid;
			private String lhc;

			@JsonInclude(Include.NON_NULL)
			@Data
			class Image {
				private String tns;
				private String url;
			}

			@JsonInclude(Include.NON_NULL)
			@Data
			class GeoLocation {
				private String ln;
				private String lt;
			}

			@JsonInclude(Include.NON_NULL)
			@Data
			class Pops {
				private ArrayList<String> fc;
				private double tpc;
				private String sn;
			}

			@JsonInclude(Include.NON_NULL)
			@Data
			class Address {
				private String adr;
				private String adr2;
				private String postalCode;
				private City city;
				private Country country;
				private String ctn;
				private String cn;

				@JsonInclude(Include.NON_NULL)
				@Data
				class City {
					private String name;
				}

				@JsonInclude(Include.NON_NULL)
				@Data
				class State {
					private String name;
				}

				@JsonInclude(Include.NON_NULL)
				@Data
				class Country {
					private String code;
					private String name;
				}
			}
		}
	}

	@JsonInclude(Include.NON_NULL)
	@Data
	class Status {
		private boolean success;
		private int httpStatus;
	}
}
