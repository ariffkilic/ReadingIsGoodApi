package com.reading.is.good.service.service.command;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CreateBookCommand {
	
	@NotNull
	private String name;
	
	@NotNull
	private String author;
	
	@NotNull
	private BigDecimal price;
	
	@NotNull
	private Long stock;

}
