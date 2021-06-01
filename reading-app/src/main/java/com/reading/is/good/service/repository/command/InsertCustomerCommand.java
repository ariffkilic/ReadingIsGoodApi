package com.reading.is.good.service.repository.command;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class InsertCustomerCommand {

	private String name;

	private String email;

	private String adress;
}
