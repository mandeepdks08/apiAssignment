package com.main.models.request;

import com.main.annotations.Required;

import lombok.Data;

@Data
public class CancellationPolicyRequest {
	@Required
	private String id;
	@Required
	private String optionId;
}
