package com.models;

import com.annotations.Required;

import lombok.Data;

@Data
public class SearchId {
	@Required
	private String searchId;
}
