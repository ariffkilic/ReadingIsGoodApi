package com.reading.is.good.service.api.error;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.reading.is.good.service.api.dto.ErrorResponse;
import com.reading.is.good.service.exception.ErrorCode;
import com.reading.is.good.service.exception.ErrorResultException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ErrorControllerAdvice {
	
	@ExceptionHandler(ErrorResultException.class)
	public ResponseEntity<ErrorResponse> handleErrorResultException(final ErrorResultException ex) {
		log.error("Exception occured {0} : " + ex.getMessage());
		return ResponseEntity.status(ex.getStatus())
				.body(ErrorResponse.builder()
					.code(ex.getCode())
					.type(ex.getType())
					.message(ex.getMessage())
					.status(ex.getStatus())
					.build());
	  }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
		return ResponseEntity.status(BAD_REQUEST)
				.body(ErrorResponse.builder()
						.code(ErrorCode.INVALID_REQUEST.name())
						.type(BAD_REQUEST.name())
						.message(ex.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", ")))
						.status(ErrorCode.INVALID_REQUEST.getStatus().value())
						.build());
	  }
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralException(final Exception ex) {
		log.error("Exception occured {0} : " + ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Please try again later");
	}
}
