package com.reading.is.good.service.service;

import com.reading.is.good.service.domain.Book;
import com.reading.is.good.service.service.command.CreateBookCommand;

public interface BookService {

	Book create(CreateBookCommand customerCreate);
	
	Book update(Long id, Long stock);

}
