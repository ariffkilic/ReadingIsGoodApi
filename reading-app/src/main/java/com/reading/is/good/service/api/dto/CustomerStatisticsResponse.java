package com.reading.is.good.service.api.dto;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CustomerStatisticsResponse {
	
	private List<CustomerStatisticResponse> statisticList;

}
