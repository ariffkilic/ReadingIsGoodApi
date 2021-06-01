package com.reading.is.good.service.service;

import com.reading.is.good.service.domain.CustomerStatistics;

public interface StatisticsService {
	
	public CustomerStatistics getCustomerStatistic(Long customerId);

}
