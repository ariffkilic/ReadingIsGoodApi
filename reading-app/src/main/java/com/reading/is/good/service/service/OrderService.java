package com.reading.is.good.service.service;

import java.util.Date;
import java.util.List;

import com.reading.is.good.service.domain.Order;
import com.reading.is.good.service.domain.OrderDetail;
import com.reading.is.good.service.service.command.CreateOrderCommand;

public interface OrderService {
	
	public Order create(CreateOrderCommand orderCreate);
	
	public OrderDetail retrieveById(Long id);
	
	public List<OrderDetail> retrieveByDate(Date startDate, Date finishDate);

}
