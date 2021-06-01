package com.reading.is.good.service.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ErrorResponse {
	
	private String type;
	
	private String code;
	
	private int status;
	
	private String message;

}
