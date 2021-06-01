package com.reading.is.good.service.exception;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
@Builder
public class ErrorResultException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String type;
	private String code;
	private String message;
	private int status;
}
