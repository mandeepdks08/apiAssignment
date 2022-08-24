package com.main.models.response;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.main.models.request.SearchQueryRequest.SearchQuery;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class HotelDetailResponse {
	private String id;
	private Hotel hotel;
	private SearchQuery searchQuery;
	private Status status;
	private ArrayList<Error> errors;
	private Object metaInfo;

	@Data
	@JsonInclude(Include.NON_NULL)
	class Hotel {
		private String id;
		private String name;
		private ArrayList<Image> img;
		private String des;
		private int rt;
		private GeoLocation gl;
		private Address ad;
		private String pt;
		private boolean iph;
		private ArrayList<Options> ops;
		private ArrayList<Pops> pops;
		private MiscInfo mi;
		private PromoInfo pr;
		private ArrayList<Object> ss;

		@Data
		@JsonInclude(Include.NON_NULL)
		class Image {
			private String tns;
			private String url;
		}

		@Data
		@JsonInclude(Include.NON_NULL)
		class GeoLocation {
			private String ln;
			private String lt;
		}

		@Data
		@JsonInclude(Include.NON_NULL)
		class Address {
			private String adr;
			private City city;
			private State state;
			private Country country;

			@Data
			@JsonInclude(Include.NON_NULL)
			class City {
				private String name;
			}

			@Data
			@JsonInclude(Include.NON_NULL)
			class State {
				private String name;
			}

			@Data
			@JsonInclude(Include.NON_NULL)
			class Country {
				private String name;
			}
		}

		@Data
		@JsonInclude(Include.NON_NULL)
		class Options {
			private ArrayList<RoomInfo> ris;
			private String id;
			private double tp;
			private Object omi;

			private boolean iopr;
			private boolean ipr;
			private boolean ipm;
			private boolean isDetailHit;

			@Data
			@JsonInclude(Include.NON_NULL)
			class RoomInfo {
				private String id;
				private String rc;
				private String rt;
				private int adt;
				private int chd;
				private String mb;
				private double tp;
				private TotalFareComponent tfcs;
				private ArrayList<PerNightPriceInfo> pis;
				private ArrayList<Object> fcs;
				private Object rmi;
				private boolean iopr;

				@Data
				@JsonInclude(Include.NON_NULL)
				class TotalFareComponent {
					private double TAF;
					private double NF;
					private double BF;
					private double MFT;
					private double TF;
					private double MF;
				}

				@Data
				@JsonInclude(Include.NON_NULL)
				class PerNightPriceInfo {
					private int day;
					private FareComponent fc;
					private AdditionalFareComponent afc;

					@Data
					@JsonInclude(Include.NON_NULL)
					class FareComponent {
						private double TAF;
						private double NF;
						private double BF;
						private double TF;
					}

					@Data
					@JsonInclude(Include.NON_NULL)
					class AdditionalFareComponent {
						private TaxAndFee TAF;

						@Data
						@JsonInclude(Include.NON_NULL)
						class TaxAndFee {
							private double MFT;
							private double MU;
							private double MF;
						}
					}
				}
			}
		}

		@Data
		@JsonInclude(Include.NON_NULL)
		class Pops {
			private ArrayList<Object> fc;
			private double pc;
		}

		@Data
		@JsonInclude(Include.NON_NULL)
		class MiscInfo {
			private String set;
		}

		@Data
		@JsonInclude(Include.NON_NULL)
		class PromoInfo {
			private boolean ilpa;
			private boolean ifta;
		}
	}

	@Data
	@JsonInclude(Include.NON_NULL)
	class Status {
		private boolean success;
		private int httpStatus;
	}

	@Data
	@JsonInclude(Include.NON_NULL)
	class Error {
		private String errCode;
		private String message;
		private String details;
	}
}
