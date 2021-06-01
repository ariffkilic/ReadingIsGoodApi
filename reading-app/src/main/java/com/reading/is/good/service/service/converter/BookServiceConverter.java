package com.reading.is.good.service.service.converter;

import com.reading.is.good.service.domain.Book;
import com.reading.is.good.service.repository.command.InsertBookCommand;
import com.reading.is.good.service.repository.command.UpdateBookCommand;
import com.reading.is.good.service.service.command.CreateBookCommand;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BookServiceConverter {
	
	public InsertBookCommand toInsertCommand(final CreateBookCommand command) {
		return InsertBookCommand.builder()
				.name(command.getName())
				.author(command.getAuthor())
				.price(command.getPrice())
				.stock(command.getStock()).
				build();
	}

	public UpdateBookCommand toUpdateBookCommand(Book book) {
		return UpdateBookCommand.builder()
				.id(book.getId())
				.stock(book.getStock())
				.build();
	}

}
