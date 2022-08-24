package com.main.models.request;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.main.annotations.Required;

import lombok.Data;

@Data
public class HotelBookingRequest {
	@Required
	private String bookingId;
	@Required
	private ArrayList<Object> roomTravellerInfo;
	private String type;
	private DeliveryInfo deliveryInfo; 
	private List<Object> paymentInfos;
	private double amount;
	
	@Data
	public static class RoomTravellerInfo {
		@Required
		private ArrayList<Object> travellerInfo;
		
		@Data
		public static class TravellerInfo {
			@Required
			private String fN;
			@Required
			private String lN;
			private String pan;
			private String pNum;
			@Required
			private String ti;
			@Required
			private String pt;
		}
	}
	
	@Data
	public static class DeliveryInfo {
		private List<Object> emails;
		private List<Object> contacts;
		private List<Object> code;
	}
}
