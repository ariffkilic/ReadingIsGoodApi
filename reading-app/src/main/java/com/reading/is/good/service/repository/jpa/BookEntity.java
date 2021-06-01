package com.reading.is.good.service.repository.jpa;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "BOOK")
public class BookEntity {
	
	@Id
	@SequenceGenerator(name = "BOOK_SEQUENCE", sequenceName = "BOOK_S", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "BOOK_SEQUENCE")
	@Column(name = "ID")
	private Long id;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String author;

	@Column(scale = 2,  precision=20)
	private BigDecimal price;
	
	@Column(nullable = false)
	private Long stock;
	
	@Column(nullable = false)
	private Instant createdAt;
	
	@Column(nullable = true)
	private Instant updatedAt;
		
	@Version
	private int version;

}
