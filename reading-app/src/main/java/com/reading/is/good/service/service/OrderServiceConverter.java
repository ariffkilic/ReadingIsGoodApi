package com.reading.is.good.service.service;

import java.time.Instant;

import com.reading.is.good.service.repository.command.InsertOrderCommand;
import com.reading.is.good.service.repository.command.SearchOrderCommand;
import com.reading.is.good.service.service.command.CreateOrderCommand;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderServiceConverter {
	
	InsertOrderCommand toInsertCommand(final CreateOrderCommand command) {
		return InsertOrderCommand.builder()
				.customerId(command.getCustomerId())
				.bookIdList(command.getBookIdList())
				.build();
	}
	
	SearchOrderCommand toSearchCommand(final Long id) {
		return SearchOrderCommand.builder()
				.id(id)
				.build();
	}
	
	SearchOrderCommand toSearchCommand(final Instant fromDate, final Instant toDate) {
		return SearchOrderCommand.builder()
				.startDate(fromDate)
				.finishDate(toDate)
				.build();
	}

}
