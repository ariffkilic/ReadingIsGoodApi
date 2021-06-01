package com.reading.is.good.service.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.text.MessageFormat;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

	INVALID_REQUEST(BAD_REQUEST, "Invalid client request : {0}"),
	BOOK_NOT_FOUND(NOT_FOUND, "Book is not exist id : {0}"),
	ORDER_NOT_FOUND(NOT_FOUND, "Order is not exist id : {0}"),
	EMAIL_REGISTERED(BAD_REQUEST, "Email exists with same mail : {0}"),
	USER_NOT_FOUND(NOT_FOUND, "User is not exist id : {0}"),
	NOT_SUFFICIENT_STOCK(BAD_REQUEST, "Book has not enough stock in book : {0}");

	private HttpStatus status;
	private String message;

	public ErrorResultException asErrorResult(final Object... params) {
		return ErrorResultException.builder().code(name()).type(status.name()).status(status.value())
				.message(MessageFormat.format(message, params)).build();
	}

}
