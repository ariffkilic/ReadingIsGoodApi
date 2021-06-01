package com.reading.is.good.service.api;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reading.is.good.service.api.converter.CustomerApiConverter;
import com.reading.is.good.service.api.dto.CustomerRequest;
import com.reading.is.good.service.api.dto.CustomerResponse;
import com.reading.is.good.service.api.dto.CustomersResponse;
import com.reading.is.good.service.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	private final CustomerService service;
	
	@PostMapping
	@ResponseStatus(CREATED)
	public CustomerResponse addCustomer(@Valid @RequestBody final CustomerRequest customerRequest){
		return CustomerApiConverter.toCustomerResponse(service.create(CustomerApiConverter.toCustomerCreate(customerRequest)));
	}
	
	@GetMapping
	@ResponseStatus(OK)
	public CustomersResponse retrieveCustomers(@RequestParam(defaultValue = "1") int page, 
			@RequestParam(defaultValue = "10") int size){
		return CustomerApiConverter.toCustomersResponse(service.retrieve(page,size));
	}

}
