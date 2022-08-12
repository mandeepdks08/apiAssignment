package com.models;

import com.annotations.Required;

import lombok.Data;

@Data
public class Id {
	@Required
	private String id;
}