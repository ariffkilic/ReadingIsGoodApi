package com.reading.is.good.service.service.converter;

import java.time.Instant;

import com.reading.is.good.service.repository.command.InsertOrderCommand;
import com.reading.is.good.service.repository.command.SearchOrderCommand;
import com.reading.is.good.service.service.command.CreateOrderCommand;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderServiceConverter {
	
	public InsertOrderCommand toInsertCommand(final CreateOrderCommand command) {
		return InsertOrderCommand.builder()
				.customerId(command.getCustomerId())
				.bookIdList(command.getBookIdList())
				.build();
	}
	
	public SearchOrderCommand toSearchCommand(final Long id) {
		return SearchOrderCommand.builder()
				.id(id)
				.build();
	}
	
	public SearchOrderCommand toSearchCommand(final Instant fromDate, final Instant toDate) {
		return SearchOrderCommand.builder()
				.startDate(fromDate)
				.finishDate(toDate)
				.build();
	}

}
