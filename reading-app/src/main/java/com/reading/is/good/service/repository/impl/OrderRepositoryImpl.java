package com.reading.is.good.service.repository.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.reading.is.good.service.domain.CustomerStatistics;
import com.reading.is.good.service.domain.Order;
import com.reading.is.good.service.domain.OrderDetail;
import com.reading.is.good.service.exception.ErrorCode;
import com.reading.is.good.service.repository.BookRepository;
import com.reading.is.good.service.repository.CustomerRepository;
import com.reading.is.good.service.repository.OrderRepository;
import com.reading.is.good.service.repository.command.InsertOrderCommand;
import com.reading.is.good.service.repository.converter.OrderRepositoryConverter;
import com.reading.is.good.service.repository.jpa.BookEntity;
import com.reading.is.good.service.repository.jpa.CustomerEntity;
import com.reading.is.good.service.repository.jpa.OrderEntity;
import com.reading.is.good.service.repository.jpa.repository.OrderJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
	
	private final BookRepository bookRepository;
	
	private final CustomerRepository customerRepository;
	
	private final OrderJpaRepository orderRepository;
	
	@Override
	public Order save(InsertOrderCommand command) {
		List<BookEntity> books = bookRepository.findInIdList(command.getBookIdList());
		BigDecimal totalAmount = BigDecimal.ZERO;
		
		for (BookEntity bookEntity : books) {
			if(!(bookEntity.getStock()>0)) {
				throw ErrorCode.NOT_SUFFICIENT_STOCK.asErrorResult(bookEntity.getId());
			}else {
				bookEntity.setStock(bookEntity.getStock()-1);
				totalAmount = totalAmount.add(bookEntity.getPrice());
			}
		}
		
		CustomerEntity customer = customerRepository.findById(command.getCustomerId());
		
		OrderEntity orderEntity = orderRepository.save(OrderRepositoryConverter.toOrderEntity(customer, books, totalAmount,books.size()));
		return OrderRepositoryConverter.toOrder(orderEntity);
	}

	@Override
	public OrderDetail retrive(Long id) {
		 OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(()-> ErrorCode.ORDER_NOT_FOUND.asErrorResult(id));
		 return OrderRepositoryConverter.toOrderDetail(orderEntity);
	}

	@Override
	public List<OrderDetail> retrive(Date startDate, Date fromDate) {
		List<OrderEntity> orderEntityList = orderRepository.findByCreatedAtBetween(startDate, fromDate);
		return OrderRepositoryConverter.toOrderDetailList(orderEntityList);
	}

	@Override
	public CustomerStatistics getCustomerStatisticByMonthly(Long customerId) {
		return OrderRepositoryConverter.toCustomerStatistics(orderRepository.getCustomerStatisticByMonthly(customerId));
	}
}
