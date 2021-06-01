package com.reading.is.good.service.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long>{
	
	boolean existsByEmail(String email);
	
}