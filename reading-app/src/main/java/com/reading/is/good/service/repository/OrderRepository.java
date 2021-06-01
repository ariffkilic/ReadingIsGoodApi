package com.reading.is.good.service.repository;

import java.util.Date;
import java.util.List;

import com.reading.is.good.service.domain.CustomerStatistics;
import com.reading.is.good.service.domain.Order;
import com.reading.is.good.service.domain.OrderDetail;
import com.reading.is.good.service.repository.command.InsertOrderCommand;

public interface OrderRepository {
	
	public Order save(InsertOrderCommand command);
	
	public OrderDetail retrive(Long id);
	
	public List<OrderDetail> retrive(Date startDate, Date fromDate);
	
	public CustomerStatistics getCustomerStatisticByMonthly(Long customerId);

}
