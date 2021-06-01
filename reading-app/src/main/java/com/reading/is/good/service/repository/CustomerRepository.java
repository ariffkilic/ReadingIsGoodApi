package com.reading.is.good.service.repository;

import com.reading.is.good.service.domain.Customer;
import com.reading.is.good.service.domain.Customers;
import com.reading.is.good.service.repository.command.InsertCustomerCommand;
import com.reading.is.good.service.repository.jpa.CustomerEntity;

public interface CustomerRepository {
	
	Customer save(final InsertCustomerCommand command);

	Customers list(int page, int size);
	
	CustomerEntity findById(Long id);

}
