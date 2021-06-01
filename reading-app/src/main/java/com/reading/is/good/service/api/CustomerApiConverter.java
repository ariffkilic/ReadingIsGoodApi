package com.reading.is.good.service.api;

import java.util.stream.Collectors;

import com.reading.is.good.service.api.dto.CustomerRequest;
import com.reading.is.good.service.api.dto.CustomerResponse;
import com.reading.is.good.service.api.dto.CustomersResponse;
import com.reading.is.good.service.domain.Customer;
import com.reading.is.good.service.domain.Customers;
import com.reading.is.good.service.service.command.CreateCustomerCommand;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerApiConverter {
	
	public static CustomerResponse toCustomerResponse(final Customer customer) {
		return CustomerResponse.builder()
				.id(customer.getId())
				.email(customer.getEmail())
				.name(customer.getName())
				.address(customer.getAddress()).build();
	}
	
	public static CustomersResponse toCustomersResponse(final Customers customers) {
		return CustomersResponse.builder()
				.totalItems(customers.getTotalItems())
				.totalPages(customers.getTotalPages())
				.currentPage(customers.getCurrentPage())
				.numberOfItemsInPage(customers.getNumberOfItemsInPage())
				.customerList(customers.getCustomerList().stream()
						.map(CustomerApiConverter::toCustomerResponse).collect(Collectors.toList()))
				.build();
	}
	
	public static CreateCustomerCommand toCustomerCreate(final CustomerRequest request) {
		return CreateCustomerCommand.builder()
				.email(request.getEmail())
				.name(request.getName())
				.address(request.getAddress()).build();
	}

}