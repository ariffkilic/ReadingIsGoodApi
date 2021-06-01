package com.reading.is.good.service.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderResponse {
	
	private Long id;
	
	private Long customerId;
	
	private String status;

}
