package com.reading.is.good.service.api.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CustomerStatisticResponse {
	
	private String month; 
	
	private int totalOrderCount;
	
	private int totalBookCount;
	
	private BigDecimal totalPurchasedAmount;

}
