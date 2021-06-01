package com.reading.is.good.service.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.reading.is.good.service.domain.Customer;
import com.reading.is.good.service.domain.Customers;
import com.reading.is.good.service.exception.ErrorCode;
import com.reading.is.good.service.repository.command.InsertCustomerCommand;
import com.reading.is.good.service.repository.jpa.CustomerEntity;
import com.reading.is.good.service.repository.jpa.CustomerJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {
	
	private final CustomerJpaRepository customerJpaRepository;

	@Override
	public Customer save(InsertCustomerCommand command) {
		if(customerJpaRepository.existsByEmail(command.getEmail())) {
			throw ErrorCode.EMAIL_REGISTERED.asErrorResult(command.getEmail());
		}

		CustomerEntity entity =  customerJpaRepository.save(RepositoryConverter.toCustomerEntity(command));
		return RepositoryConverter.toCustomer(entity);
	} 

	@Override
	public Customers list(int page, int size) {
		return RepositoryConverter.toCustomers(customerJpaRepository.findAll(PageRequest.of(page-1, size)));
	}

	@Override
	public CustomerEntity findById(Long id) {
		return customerJpaRepository.findById(id).orElseThrow(() -> ErrorCode.USER_NOT_FOUND.asErrorResult(id));
	}

}
