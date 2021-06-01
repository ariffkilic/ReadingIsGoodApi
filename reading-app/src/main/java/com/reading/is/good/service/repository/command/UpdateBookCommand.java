package com.reading.is.good.service.repository.command;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UpdateBookCommand {
	
	private Long id;
	
	private Long stock;

}
