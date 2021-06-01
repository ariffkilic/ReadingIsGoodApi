package com.reading.is.good.service.repository.jpa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.reading.is.good.service.repository.jpa.CustomerStatisticEntity;
import com.reading.is.good.service.repository.jpa.OrderEntity;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long>{
	
	public List<OrderEntity> findByCreatedAtBetween(Date startDate,Date finishDate);
	
	@Query(value = "SELECT EXTRACT(MONTH FROM CREATED_AT) AS month, count(ID) AS totalOrder , sum(PIECE) AS totalPiece, sum(TOTAL_AMOUNT) AS totalAmount FROM ORDERS"
			+ " WHERE CUSTOMER_ID = ? GROUP BY EXTRACT(MONTH FROM CREATED_AT)", nativeQuery = true)
	public List<CustomerStatisticEntity> getCustomerStatisticByMonthly(Long customerId);
	
}
