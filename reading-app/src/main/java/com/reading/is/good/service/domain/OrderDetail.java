package com.reading.is.good.service.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
	
	private Long id;
	
	private Customer customer;
	
	private List<Book> bookList;

}
