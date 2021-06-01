package com.reading.is.good.service.api;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reading.is.good.service.api.dto.CustomerStatisticsResponse;
import com.reading.is.good.service.service.StatisticsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
	
	private final StatisticsService service;

	@GetMapping(value = "/{id}")
	@ResponseStatus(OK)
	public @ResponseBody CustomerStatisticsResponse retrieveById(@PathVariable("id") Long id){
		return StatisticsApiConverter.toCustomerStatisticsResponse(service.getCustomerStatistic(id));
	}
	
}
