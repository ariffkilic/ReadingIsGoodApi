package com.reading.is.good.service.repository.converter;

import java.time.Instant;

import com.reading.is.good.service.domain.Book;
import com.reading.is.good.service.repository.command.InsertBookCommand;
import com.reading.is.good.service.repository.jpa.BookEntity;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BookRepositoryConverter {
	
	public Book toBook(final BookEntity entity) {
		var book = new Book();
		book.setId(entity.getId());
		book.setName(entity.getName());
		book.setAuthor(entity.getAuthor());
		book.setPrice(entity.getPrice());;
		book.setStock(entity.getStock());
		return book;
	}
	
	public BookEntity toBookEntity(final InsertBookCommand command) {
		return BookEntity.builder()
				.name(command.getName())
				.author(command.getAuthor())
				.stock(command.getStock())
				.price(command.getPrice())
				.createdAt(Instant.now())
				.build();
	}

}
