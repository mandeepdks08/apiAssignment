package com.main.models.response;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class CancellationPolicyResponse {
	private String id;
	private Object cancellationPolicy;
	private Status status;

	@Data
	@JsonInclude(Include.NON_NULL)
	class CancellationPolicy {
		private String id;
		private boolean ifra;
		private ArrayList<PenaltyDetails> pd;
		private String notes;
		private MiscInfo mi;

		@Data
		@JsonInclude(Include.NON_NULL)
		class PenaltyDetails {
			private String fdt;
			private String tdt;
			private double am;
		}

		@Data
		@JsonInclude(Include.NON_NULL)
		class MiscInfo {
			private boolean iba;
			private boolean iso;
			private String hn;
			private String ad;
		}
	}

	@Data
	@JsonInclude(Include.NON_NULL)
	class Status {
		private boolean success;
		private int httpStatus;
	}
}
