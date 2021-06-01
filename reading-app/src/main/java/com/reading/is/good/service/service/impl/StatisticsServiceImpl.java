package com.reading.is.good.service.service.impl;

import org.springframework.stereotype.Service;

import com.reading.is.good.service.domain.CustomerStatistics;
import com.reading.is.good.service.repository.OrderRepository;
import com.reading.is.good.service.service.StatisticsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
	
	private final OrderRepository orderRepository;
	
	@Override
	public CustomerStatistics getCustomerStatistic(Long customerId) {
		return orderRepository.getCustomerStatisticByMonthly(customerId);
	}

}
 