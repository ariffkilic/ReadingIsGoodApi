package com.reading.is.good.service.repository.jpa;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CUSTOMER")
public class CustomerEntity {

	@Id
	@SequenceGenerator(name = "CUSTOMER_SEQUENCE", sequenceName = "CUSTOMER_S", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "CUSTOMER_SEQUENCE")
	@Column(name = "ID")
	private Long id;
	
	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String adress;

	@Column(nullable = false)
	private Instant createdAt;

}
