package com.reading.is.good.service.domain;

import java.util.List;

import com.reading.is.good.service.constants.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	
	private Long id;
	
	private Long customerId;
	
	private List<Long> bookIdList;
	
	private OrderStatus status;

}
