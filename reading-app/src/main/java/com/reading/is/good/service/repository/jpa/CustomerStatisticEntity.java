package com.reading.is.good.service.repository.jpa;

import java.math.BigDecimal;

public interface CustomerStatisticEntity {
	
	int getMonth();
	
	int getTotalOrder();
	
	int getTotalPiece();
	
	BigDecimal getTotalAmount();
}
