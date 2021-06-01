package com.reading.is.good.service.service;

import javax.validation.Valid;

import com.reading.is.good.service.domain.Customer;
import com.reading.is.good.service.domain.Customers;
import com.reading.is.good.service.service.command.CreateCustomerCommand;

public interface CustomerService {
	
	Customer create(final @Valid CreateCustomerCommand command);
	
	Customers retrieve(int page, int size);

}
