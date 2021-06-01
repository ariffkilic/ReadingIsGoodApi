package com.reading.is.good.service.api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CustomersResponse {
	
	private long totalItems;
	
	private long totalPages;
	
	private long currentPage;
	
	private long numberOfItemsInPage;
	
	private List<CustomerResponse> customerList;

}
