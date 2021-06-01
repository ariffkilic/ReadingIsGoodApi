package com.reading.is.good.service.api;

import static org.springframework.http.HttpStatus.CREATED;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reading.is.good.service.api.dto.BookRequest;
import com.reading.is.good.service.api.dto.BookResponse;
import com.reading.is.good.service.service.BookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {
	
	private final BookService service;
	
	@PostMapping
	@ResponseStatus(CREATED)
	public BookResponse addBook(@Valid @RequestBody BookRequest bookRequest){
		return BookApiConverter.toBookResponse(service.create(BookApiConverter.toBookCreate(bookRequest)));
	}
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(CREATED)
	public BookResponse updateBook(@Valid @PathVariable(value = "id") Long id, 
			@Min(value = 1, message = "Stock must be greater than zero") @RequestBody Long stock ){
		return BookApiConverter.toBookResponse(service.update(id, stock));
	}

}
