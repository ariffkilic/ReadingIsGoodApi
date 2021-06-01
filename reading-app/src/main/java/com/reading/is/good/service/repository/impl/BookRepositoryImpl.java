package com.reading.is.good.service.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.reading.is.good.service.domain.Book;
import com.reading.is.good.service.exception.ErrorCode;
import com.reading.is.good.service.repository.BookRepository;
import com.reading.is.good.service.repository.command.InsertBookCommand;
import com.reading.is.good.service.repository.converter.BookRepositoryConverter;
import com.reading.is.good.service.repository.jpa.BookEntity;
import com.reading.is.good.service.repository.jpa.repository.BookJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

	private final BookJpaRepository bookJpaRepository;

	@Override
	public Book save(InsertBookCommand command) {
		return BookRepositoryConverter.toBook(bookJpaRepository.save(BookRepositoryConverter.toBookEntity(command)));
	}

	@Override
	public List<BookEntity> findInIdList(List<Long> idlist) {
		return bookJpaRepository.findAllById(idlist);
	}

	@Override
	public Book update(Long id, Long stock) {
		BookEntity bookEntity = bookJpaRepository.findById(id).orElseThrow(() -> ErrorCode.BOOK_NOT_FOUND.asErrorResult(id));
		bookEntity.setStock(stock);
		return BookRepositoryConverter.toBook(bookJpaRepository.save(bookEntity));
	}

}
