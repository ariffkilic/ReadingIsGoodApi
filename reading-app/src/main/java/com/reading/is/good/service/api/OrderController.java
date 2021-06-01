package com.reading.is.good.service.api;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reading.is.good.service.api.converter.OrderApiConverter;
import com.reading.is.good.service.api.dto.OrderDetailResponse;
import com.reading.is.good.service.api.dto.OrderRequest;
import com.reading.is.good.service.api.dto.OrderResponse;
import com.reading.is.good.service.service.OrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	private final OrderService service;
	
	@PostMapping
	@ResponseStatus(CREATED)
	public OrderResponse addOrder(@Valid @RequestBody OrderRequest orderRequest){
		return OrderApiConverter.toOrderResponse(service.create(OrderApiConverter.toOrderCreate(orderRequest)));
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(OK)
	public @ResponseBody OrderDetailResponse retrieveById(@PathVariable("id") Long id){
		return OrderApiConverter.toOrderDetailResponse(service.retrieveById(id));
	}
	
	@GetMapping
	@ResponseStatus(OK)
	public @ResponseBody List<OrderDetailResponse> listByDate(@RequestParam("from") @DateTimeFormat (pattern="yyyy-MM-dd",iso = ISO.DATE_TIME) Date fromDate,
			@RequestParam("to") @DateTimeFormat (pattern="yyyy-MM-dd",iso = ISO.DATE_TIME) Date toDate){
		return OrderApiConverter.toOrderDetailResponseList(service.retrieveByDate(fromDate, toDate));
	}

}
