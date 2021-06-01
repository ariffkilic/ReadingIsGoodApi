package com.reading.is.good.service.repository.jpa;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.reading.is.good.service.constants.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ORDERS")
public class OrderEntity {
	
	@Id
	@SequenceGenerator(name = "ORDERS_SEQUENCE", sequenceName = "ORDERS_S", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "ORDERS_SEQUENCE")
	@Column(name = "ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private CustomerEntity customer;
	
	@ManyToMany
	@JoinTable(
		name = "ORDER_BOOK",
		joinColumns = @JoinColumn(name="ORDER_ID"),
		inverseJoinColumns = @JoinColumn(name="BOOK_ID")
	)
	private Set<BookEntity> bookList;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@Column
	private int piece;
	
	@Column(scale = 2,  precision=20)
	private BigDecimal totalAmount;
	
	@Column(nullable = false)
	private Date createdAt;
	
	@Column
	private Date updatedAt;

}
