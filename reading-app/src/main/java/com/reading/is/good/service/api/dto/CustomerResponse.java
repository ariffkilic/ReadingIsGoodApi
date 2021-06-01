package com.reading.is.good.service.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CustomerResponse {

	private Long id;
	
	private String email;
	
	private String name;
	
	private String address;

}
