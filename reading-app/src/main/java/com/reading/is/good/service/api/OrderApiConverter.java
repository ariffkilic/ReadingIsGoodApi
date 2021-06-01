package com.reading.is.good.service.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.reading.is.good.service.api.dto.OrderDetailResponse;
import com.reading.is.good.service.api.dto.OrderRequest;
import com.reading.is.good.service.api.dto.OrderResponse;
import com.reading.is.good.service.domain.Order;
import com.reading.is.good.service.domain.OrderDetail;
import com.reading.is.good.service.service.command.CreateOrderCommand;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderApiConverter {
	
	public static CreateOrderCommand toOrderCreate(final OrderRequest request) {
		return CreateOrderCommand.builder()
				.customerId(request.getCustomerId())
				.bookIdList(request.getBookIdList())
				.build();
	}
	
	public static OrderResponse toOrderRetrieve(Order create) {
		return OrderResponse.builder()
				.id(create.getId())
				.customerId(create.getCustomerId())
				.status(create.getStatus().toString())
				.build();
	}

	public static OrderResponse toOrderResponse(Order create) {
		return OrderResponse.builder()
				.id(create.getId())
				.customerId(create.getCustomerId())
				.status(create.getStatus().toString())
				.build();
	}
	
	public static OrderDetailResponse toOrderDetailResponse(OrderDetail detail) {
		return OrderDetailResponse.builder()
				.id(detail.getId())
				.customerResponse(CustomerApiConverter.toCustomerResponse(detail.getCustomer()))
				.bookList(detail.getBookList().stream().map(BookApiConverter::toBookResponse).collect(Collectors.toList()))
				.build();
	}
	
	public static List<OrderDetailResponse> toOrderDetailResponseList(List<OrderDetail> orderDetailList) {
		List<OrderDetailResponse> orderDetailResponseList = new ArrayList<>();
		for (OrderDetail orderDetail : orderDetailList) {
			orderDetailResponseList.add(toOrderDetailResponse(orderDetail));
		}
		return orderDetailResponseList;
	}
	
}
