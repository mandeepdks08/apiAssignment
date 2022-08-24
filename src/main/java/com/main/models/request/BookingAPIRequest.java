package com.main.models.request;

import java.util.ArrayList;

public class BookingAPIRequest {
	private String bookingId;
	private ArrayList<RoomTravellerInfo> roomTravellerInfo;
	private String type;
	private ArrayList<PaymentInfo> paymentInfos;
	
	static public class RoomTravellerInfo {
		private ArrayList<TravellerInfo> travellerInfo;
		
		static public class TravellerInfo {
			private String fN;
			private String lN;
			private String pan;
			private String pNum;
			private String ti;
			private String pt;
		}
	}
	
	static public class DeliveryInfo {
		private ArrayList<String> emails;
		private ArrayList<String> contacts;
		private ArrayList<String> code;
	}
	
	static public class PaymentInfo {
		private Double amount;
	}
}
