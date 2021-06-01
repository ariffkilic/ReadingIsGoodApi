package com.reading.is.good.service.repository.command;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class InsertOrderCommand {
	
	private Long customerId;
	
	private List<Long> bookIdList;
	
}
