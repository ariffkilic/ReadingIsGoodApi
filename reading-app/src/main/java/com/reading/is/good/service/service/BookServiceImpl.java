package com.reading.is.good.service.service;

import org.springframework.stereotype.Service;

import com.reading.is.good.service.domain.Book;
import com.reading.is.good.service.repository.BookRepository;
import com.reading.is.good.service.service.command.CreateBookCommand;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookRepository repository;

	@Override
	public Book create(CreateBookCommand customerCreate) {
		return repository.save(BookServiceConverter.toInsertCommand(customerCreate));
	}

	@Override
	public Book update(Long id, Long stock) {
		return repository.update(id, stock);
	}

}
