package com.reading.is.good.service.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerStatistic {

	private String month; 
	
	private int totalOrderCount;
	
	private int totalBookCount;
	
	private BigDecimal totalPurchasedAmount;
	
}
