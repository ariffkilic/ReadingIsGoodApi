package com.reading.is.good.service.api.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class BookRequest {

	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@NotBlank(message = "Author is mandatory")
	private String author;
	
	@NotNull(message = "Price must not be null")
	@Min(value = 1, message = "Price must be greater than 0" )
	private BigDecimal price;
	
	@NotNull(message = "Stock must not be null")
	@Min(value = 1, message = "Stock must be greater than 0" )
	private Long stock;

}
