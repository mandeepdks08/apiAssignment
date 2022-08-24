package com.main.models;

import com.main.annotations.Required;

import lombok.Data;

@Data
public class SearchId {
	@Required
	private String searchId;
}
