package com.reading.is.good.service.service;

import com.reading.is.good.service.repository.command.InsertCustomerCommand;
import com.reading.is.good.service.service.command.CreateCustomerCommand;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerServiceConverter {
	
	InsertCustomerCommand toInsertCommand(final CreateCustomerCommand command) {
		return InsertCustomerCommand.builder()
				.name(command.getName())
				.email(command.getEmail())
				.adress(command.getAddress()).build();
	}
	
	

}
