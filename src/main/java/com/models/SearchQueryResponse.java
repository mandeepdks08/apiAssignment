package com.models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
class Status {
	private boolean success;
	private int httpStatus;
}

@JsonInclude(Include.NON_NULL)
@Data
public class SearchQueryResponse {
	private ArrayList<String> searchIds;
	private Status status;
	Object metaInfo;
}
