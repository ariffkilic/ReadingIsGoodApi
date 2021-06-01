package com.reading.is.good.service.api.dto;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CustomerRequest {
	
	@NotBlank(message = "Email is mandatory")
	private String email;
	
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@NotBlank(message = "Address is mandatory")
	private String address;

}
