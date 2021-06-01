package com.reading.is.good.service.repository;

import java.util.List;

import com.reading.is.good.service.domain.Book;
import com.reading.is.good.service.repository.command.InsertBookCommand;
import com.reading.is.good.service.repository.jpa.BookEntity;

public interface BookRepository {
	
	Book save(final InsertBookCommand command);
	
	Book update(Long id, Long stock); 
	
	List<BookEntity> findInIdList(List<Long> idlist);

}
