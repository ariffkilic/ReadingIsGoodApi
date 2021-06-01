package com.reading.is.good.service.api;

import java.util.stream.Collectors;

import com.reading.is.good.service.api.dto.CustomerStatisticResponse;
import com.reading.is.good.service.api.dto.CustomerStatisticsResponse;
import com.reading.is.good.service.api.dto.OrderDetailResponse;
import com.reading.is.good.service.domain.CustomerStatistic;
import com.reading.is.good.service.domain.CustomerStatistics;
import com.reading.is.good.service.domain.OrderDetail;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StatisticsApiConverter {
	
	public static OrderDetailResponse toOrderDetailResponse(OrderDetail detail) {
		return OrderDetailResponse.builder()
				.id(detail.getId())
				.build();
	}
	
	public static CustomerStatisticsResponse toCustomerStatisticsResponse(CustomerStatistics statistics) {
		return CustomerStatisticsResponse.builder()
				.statisticList(statistics.getStatisticList().stream().map(StatisticsApiConverter::toCustomerStatisticResponse).collect(Collectors.toList()))
				.build();
	}
	
	CustomerStatisticResponse toCustomerStatisticResponse(CustomerStatistic statistic) {
		return CustomerStatisticResponse.builder()
				.month(statistic.getMonth())
				.totalBookCount(statistic.getTotalBookCount())
				.totalOrderCount(statistic.getTotalOrderCount())
				.totalPurchasedAmount(statistic.getTotalPurchasedAmount())
				.build();
	}

}
