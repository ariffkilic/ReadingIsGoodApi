package com.reading.is.good.service.repository.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.reading.is.good.service.constants.Constants;
import com.reading.is.good.service.constants.OrderStatus;
import com.reading.is.good.service.domain.Book;
import com.reading.is.good.service.domain.Customer;
import com.reading.is.good.service.domain.CustomerStatistic;
import com.reading.is.good.service.domain.CustomerStatistics;
import com.reading.is.good.service.domain.Order;
import com.reading.is.good.service.domain.OrderDetail;
import com.reading.is.good.service.repository.jpa.BookEntity;
import com.reading.is.good.service.repository.jpa.CustomerEntity;
import com.reading.is.good.service.repository.jpa.CustomerStatisticEntity;
import com.reading.is.good.service.repository.jpa.OrderEntity;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderRepositoryConverter {
	
	public Customer toCustomer(final CustomerEntity entity) {
		var customer = new Customer();
		customer.setId(entity.getId());
		customer.setEmail(entity.getEmail());
		customer.setName(entity.getName());
		customer.setAddress(entity.getAdress());
		return customer;
	}
	
	public List<Book> toBookList(final Set<BookEntity> entityList) {
		List<Book> bookList = new ArrayList<>();
		for (BookEntity bookEntity : entityList) {
			var book = new Book();
			book.setId(bookEntity.getId());
			book.setName(bookEntity.getName());
			book.setAuthor(bookEntity.getAuthor());
			book.setStock(bookEntity.getStock());
			bookList.add(book);
		}
		return bookList;
	}

	public OrderEntity toOrderEntity(CustomerEntity customer, List<BookEntity> books, BigDecimal totalAmout, int piece) {
		return OrderEntity.builder()
				.customer(customer)
				.bookList(Set.copyOf(books))
				.totalAmount(totalAmout)
				.piece(piece)
				.createdAt(new Date())
				.status(OrderStatus.SUCCESSFUL)
				.build();
	}
	
	public static Order toOrder(OrderEntity orderEntity) {
		var order = new Order();
		order.setId(orderEntity.getId());
		order.setCustomerId(orderEntity.getCustomer().getId());
		order.setStatus(orderEntity.getStatus());
		return order;
	}
	
	public static OrderDetail toOrderDetail(OrderEntity orderEntity) {
		var order = new OrderDetail();
		order.setCustomer(toCustomer(orderEntity.getCustomer()));
		order.setBookList(toBookList(orderEntity.getBookList()));
		order.setId(orderEntity.getId());
		return order;
	}
	
	public static List<OrderDetail> toOrderDetailList(List<OrderEntity> orderEntity) {
		List<OrderDetail> orderDetailList = new ArrayList<>();
		for (OrderEntity entity : orderEntity) {
			orderDetailList.add(toOrderDetail(entity));
		}
		return orderDetailList;
	}
	
	public static CustomerStatistics toCustomerStatistics(List<CustomerStatisticEntity> orderEntity) {
		CustomerStatistics customerStatistics = new CustomerStatistics();
		customerStatistics.setStatisticList(orderEntity.stream().map(OrderRepositoryConverter::toCustomerStatistic).collect(Collectors.toList()));
		return customerStatistics;
	}
	
	CustomerStatistic toCustomerStatistic(CustomerStatisticEntity orderEntity) {
		CustomerStatistic customerStatistic = new CustomerStatistic();
		customerStatistic.setMonth(Constants.MONTHS[orderEntity.getMonth()]);
		customerStatistic.setTotalBookCount(orderEntity.getTotalPiece());
		customerStatistic.setTotalOrderCount(orderEntity.getTotalOrder());
		customerStatistic.setTotalPurchasedAmount(orderEntity.getTotalAmount());
		return customerStatistic;
	}
}
