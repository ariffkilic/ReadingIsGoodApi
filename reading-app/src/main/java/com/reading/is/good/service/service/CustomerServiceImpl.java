package com.reading.is.good.service.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.reading.is.good.service.domain.Customer;
import com.reading.is.good.service.domain.Customers;
import com.reading.is.good.service.repository.CustomerRepository;
import com.reading.is.good.service.service.command.CreateCustomerCommand;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository customerRepository;

	@Override
	public Customer create(@Valid CreateCustomerCommand command) {
		return customerRepository.save(CustomerServiceConverter.toInsertCommand(command));
	}

	@Override
	public Customers retrieve(int page, int size) {
		return customerRepository.list(page,size);
	}

}
