package com.reading.is.good.service.api.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class OrderRequest {
	
	@NotNull(message = "Customer Id List must not be null")
	private Long customerId;
	
	@NotNull(message = "Book Id List must not be null")
	private List<Long> bookIdList;

}
