package com.reading.is.good.service.repository.converter;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.reading.is.good.service.domain.Customer;
import com.reading.is.good.service.domain.Customers;
import com.reading.is.good.service.repository.command.InsertCustomerCommand;
import com.reading.is.good.service.repository.jpa.CustomerEntity;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerRepositoryConverter {
	
	public CustomerEntity toCustomerEntity(final InsertCustomerCommand command) {
		return CustomerEntity.builder()
				.name(command.getName())
				.email(command.getEmail())
				.adress(command.getAdress())
				.createdAt(Instant.now())
				.build();
	}
	
	public 	Customer toCustomer(final CustomerEntity entity) {
		var customer = new Customer();
		customer.setId(entity.getId());
		customer.setEmail(entity.getEmail());
		customer.setName(entity.getName());
		customer.setAddress(entity.getAdress());
		return customer;
	}
	
	public 	Customers toCustomers(final Page<CustomerEntity> page) {
		var customer = new Customers();
		customer.setTotalItems(page.getTotalElements());
		customer.setNumberOfItemsInPage(page.getNumberOfElements());
		customer.setCurrentPage(page.getNumber()+1);
		customer.setTotalPages(page.getTotalPages());
		customer.setCustomerList(page.getContent().stream().
				map(OrderRepositoryConverter::toCustomer).collect(Collectors.toList()));
		return customer;
	}

}
