package com.reading.is.good.service.repository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.reading.is.good.service.constants.OrderStatus;
import com.reading.is.good.service.domain.Order;
import com.reading.is.good.service.domain.OrderDetail;
import com.reading.is.good.service.exception.ErrorResultException;
import com.reading.is.good.service.repository.command.InsertOrderCommand;
import com.reading.is.good.service.repository.impl.OrderRepositoryImpl;
import com.reading.is.good.service.repository.jpa.BookEntity;
import com.reading.is.good.service.repository.jpa.CustomerEntity;
import com.reading.is.good.service.repository.jpa.OrderEntity;
import com.reading.is.good.service.repository.jpa.repository.OrderJpaRepository;

@ExtendWith(MockitoExtension.class)
public class OrderRepositoryImplTest {
	@InjectMocks
	private OrderRepositoryImpl repository;
	
	@Mock
	private BookRepository bookRepository;
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Mock
	private OrderJpaRepository orderJpaepository;
	
	@Test
	public void save_shouldOrderData_whenBookStockIsValid() {
		BOOK_ENTITY.setStock(BOOK_STOCK_VALID);
		
		when(bookRepository.findInIdList(BOOKID_LIST)).thenReturn(Arrays.asList(BOOK_ENTITY));
		when(orderJpaepository.save(Mockito.any())).thenReturn(ORDER_ENTITY);
		
		Order result = repository.save(INSERT_ORDER_COMMAND);
		
		assertThat(result.getId()).isEqualTo(ID);
		assertThat(result.getStatus()).isEqualTo(STATUS);
	}
	
	@Test
	public void save_shouldThrowException_whenBookStockIsInValid() {
		BOOK_ENTITY.setStock(BOOK_STOCK_INVALID);
		
		when(bookRepository.findInIdList(BOOKID_LIST)).thenReturn(Arrays.asList(BOOK_ENTITY));
		
		assertThatThrownBy(() -> repository.save(INSERT_ORDER_COMMAND)).isInstanceOf(ErrorResultException.class)
		.hasMessageContaining("Book has not enough stock");
	}
	
	@Test
	public void retrive_shouldReturnOrderDetail_whenOrderIdIsValid() {
		when(orderJpaepository.findById(ID)).thenReturn(Optional.of(ORDER_ENTITY));
		OrderDetail result = repository.retrive(ID);
		
		assertThat(result.getId()).isEqualTo(ID);
		assertThat(result.getCustomer().getId()).isEqualTo(ID);
		assertThat(result.getBookList().get(0).getName()).isEqualTo(BOOK_NAME);
		assertThat(result.getBookList().get(0).getAuthor()).isEqualTo(BOOK_AUTHOR);
	}
	
	@Test
	public void retrive_shouldThrowException_whenOrderIdIsInValid() {
		when(orderJpaepository.findById(Mockito.any())).thenReturn(Optional.empty());
		
		assertThatThrownBy(() -> repository.retrive(ID_INVALID)).isInstanceOf(ErrorResultException.class)
		.hasMessageContaining("Order is not exist id");
	}
	
	
	@BeforeAll
	public static void init() {
		INSERT_ORDER_COMMAND = InsertOrderCommand.builder().customerId(CUSTOMER_ID).bookIdList(BOOKID_LIST).build();
		BOOK_ENTITY = BookEntity.builder().name(BOOK_NAME).author(BOOK_AUTHOR).stock(CUSTOMER_ID).price(PRICE).build();
		CUSTOMER_ENTITY = CustomerEntity.builder().id(ID).build();
		ORDER_ENTITY = OrderEntity.builder().id(ID).status(STATUS).customer(CUSTOMER_ENTITY).bookList(Set.of(BOOK_ENTITY)).build();
	}
	
	private static Long ID = 12L;
	private static Long ID_INVALID = 12L;
	private static Long CUSTOMER_ID = 123L;
	private static List<Long> BOOKID_LIST = Arrays.asList(1L,2L,3L);
	
	private static String BOOK_NAME = "BOOK_NAME";
	private static String BOOK_AUTHOR = "BOOK_AUTHOR";
	private static Long BOOK_STOCK_VALID  = 3L;
	private static Long BOOK_STOCK_INVALID  = 0L;
	private static BigDecimal PRICE = BigDecimal.valueOf(5);
	
	private static OrderStatus STATUS = OrderStatus.SUCCESSFUL;
	
	private static InsertOrderCommand INSERT_ORDER_COMMAND;
	private static BookEntity BOOK_ENTITY;
	private static OrderEntity ORDER_ENTITY;
	private static CustomerEntity CUSTOMER_ENTITY;

}
