package com.reading.is.good.service.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.reading.is.good.service.domain.Book;
import com.reading.is.good.service.exception.ErrorCode;
import com.reading.is.good.service.repository.command.InsertBookCommand;
import com.reading.is.good.service.repository.jpa.BookEntity;
import com.reading.is.good.service.repository.jpa.BookJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

	private final BookJpaRepository bookJpaRepository;

	@Override
	public Book save(InsertBookCommand command) {
		return RepositoryConverter.toBook(bookJpaRepository.save(RepositoryConverter.toBookEntity(command)));
	}

	@Override
	public List<BookEntity> findInIdList(List<Long> idlist) {
		return bookJpaRepository.findAllById(idlist);
	}

	@Override
	public Book update(Long id, Long stock) {
		BookEntity bookEntity = bookJpaRepository.findById(id).orElseThrow(() -> ErrorCode.BOOK_NOT_FOUND.asErrorResult(id));
		bookEntity.setStock(stock);
		return RepositoryConverter.toBook(bookJpaRepository.save(bookEntity));
	}

}
