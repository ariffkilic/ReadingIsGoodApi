package com.reading.is.good.service.service.command;

import java.time.Instant;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RetrieveOrderCommand {
	
	private Long id;
	
	private Instant startDate;
	
	private Instant finishDate;

}
