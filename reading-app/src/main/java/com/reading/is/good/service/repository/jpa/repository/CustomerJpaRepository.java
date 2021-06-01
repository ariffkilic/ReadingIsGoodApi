package com.reading.is.good.service.repository.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reading.is.good.service.repository.jpa.CustomerEntity;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long>{
	
	boolean existsByEmail(String email);
	
}