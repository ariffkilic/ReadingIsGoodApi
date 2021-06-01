package com.reading.is.good.service.repository.command;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class InsertBookCommand {
	
	private String name;
	
	private String author;
	
	private BigDecimal price;
	
	private Long stock;

}
