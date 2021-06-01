package com.reading.is.good.service.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	
	private Long id;
	
	private String name;
	
	private String author;
	
	private BigDecimal price;
	
	private Long stock;
	
}
