package com.reading.is.good.service.service.command;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CreateCustomerCommand {

	  @NotNull
	  private String name;
	  
	  @NotNull
	  private String email;

	  @NotNull
	  private String address;

}
