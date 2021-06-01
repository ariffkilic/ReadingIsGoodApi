package com.reading.is.good.service.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class OrderDetailResponse {
	
	@JsonProperty("id")
	private Long id;

	@JsonProperty("customer")
	private CustomerResponse customerResponse;
	
	@JsonProperty("books")
	private List<BookResponse> bookList;
}
