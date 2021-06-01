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
public class Customers {
	
	private long totalItems;
	
	private long totalPages;
	
	private long currentPage;
	
	private long numberOfItemsInPage;
	
	private List<Customer> customerList;

}
