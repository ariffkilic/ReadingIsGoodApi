package com.reading.is.good.service.api.converter;

import com.reading.is.good.service.api.dto.BookRequest;
import com.reading.is.good.service.api.dto.BookResponse;
import com.reading.is.good.service.domain.Book;
import com.reading.is.good.service.service.command.CreateBookCommand;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BookApiConverter {
	
	public CreateBookCommand toBookCreate(final BookRequest request) {
		return CreateBookCommand.builder()
				.name(request.getName())
				.author(request.getAuthor())
				.price(request.getPrice())
				.stock(request.getStock()).build();
	}
	
	
	public BookResponse toBookResponse(Book book) {
		return BookResponse.builder()
				.id(book.getId())
				.author(book.getAuthor())
				.name(book.getName())
				.price(book.getPrice())
				.stock(book.getStock()).build();
	}

}
