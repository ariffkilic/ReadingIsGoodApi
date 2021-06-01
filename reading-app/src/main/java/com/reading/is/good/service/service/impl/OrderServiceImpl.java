package com.reading.is.good.service.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.reading.is.good.service.domain.Order;
import com.reading.is.good.service.domain.OrderDetail;
import com.reading.is.good.service.repository.OrderRepository;
import com.reading.is.good.service.service.OrderService;
import com.reading.is.good.service.service.command.CreateOrderCommand;
import com.reading.is.good.service.service.converter.OrderServiceConverter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	
	private final OrderRepository orderRepository;
	
	@Override
	public Order create(CreateOrderCommand orderCreate) {
		return orderRepository.save(OrderServiceConverter.toInsertCommand(orderCreate));
	}

	@Override
	public OrderDetail retrieveById(Long id) {
		return orderRepository.retrive(id);
	}

	@Override
	public List<OrderDetail> retrieveByDate(Date startDate, Date finishDate) {
		return orderRepository.retrive(startDate, finishDate);
	}

}
