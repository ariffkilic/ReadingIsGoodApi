package com.reading.is.good.service.api.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BookResponse {
	
	private Long id;
	private String name;
	private String author;
	private BigDecimal price;
	private Long stock;
	
}
