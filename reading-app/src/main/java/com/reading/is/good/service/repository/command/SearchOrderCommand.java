package com.reading.is.good.service.repository.command;

import java.time.Instant;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class SearchOrderCommand {
	
	private Long id;
	
	private Instant startDate;
	
	private Instant finishDate;

}
