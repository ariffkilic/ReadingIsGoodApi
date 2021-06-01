package com.reading.is.good.service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	private Long id;
	
	private String email;
	
	private String name;
	
	private String address;
}
