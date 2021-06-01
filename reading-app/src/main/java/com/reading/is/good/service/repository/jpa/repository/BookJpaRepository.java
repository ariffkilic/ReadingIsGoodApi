package com.reading.is.good.service.repository.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reading.is.good.service.repository.jpa.BookEntity;

public interface BookJpaRepository extends JpaRepository<BookEntity, Long>{

}
