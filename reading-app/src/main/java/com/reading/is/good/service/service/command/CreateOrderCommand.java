package com.reading.is.good.service.service.command;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CreateOrderCommand {
	
	private Long customerId;
	
	private List<Long> bookIdList;

}
