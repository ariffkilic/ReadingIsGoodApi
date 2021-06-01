package com.reading.is.good.service.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.reading.is.good.service.exception.ErrorResultException;
import com.reading.is.good.service.repository.command.InsertCustomerCommand;
import com.reading.is.good.service.repository.jpa.CustomerEntity;
import com.reading.is.good.service.repository.jpa.CustomerJpaRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerRepositoryImplTest {
	
	@InjectMocks
	private CustomerRepositoryImpl repository;
	
	@Mock
	private CustomerJpaRepository customerJpaRepository;
	
	@Test
	public void save_shouldReturnCustomer_whenEmailIsValid() {
		when(customerJpaRepository.existsByEmail(EMAIL_VALID)).thenReturn(false);
		when(customerJpaRepository.save(Mockito.any())).thenReturn(CUSTOMER_ENTITY);
		
		var result = repository.save(INSERT_CUSTOMER_COMMAND);
		assertThat(result.getId()).isEqualTo(ID);
	}
	
	@Test
	public void save_throwException_whenEmailIsInValid() {
		when(customerJpaRepository.existsByEmail(EMAIL_INVALID)).thenReturn(true);
		
		assertThatThrownBy(() -> repository.save(INSERT_CUSTOMER_COMMAND_INVALID)).isInstanceOf(ErrorResultException.class)
		.hasMessageContaining("Email exists with same mail");
	}
	
	@BeforeAll
	public static void init() {
		CUSTOMER_ENTITY = CustomerEntity.builder().id(ID).build();
		INSERT_CUSTOMER_COMMAND = InsertCustomerCommand.builder().email(EMAIL_VALID).build();
		INSERT_CUSTOMER_COMMAND_INVALID = InsertCustomerCommand.builder().email(EMAIL_INVALID).build();
	}
	
	
	private static Long ID = 12L;
	private static String EMAIL_VALID = "a@gmail.com";
	private static String EMAIL_INVALID = "invalid@gmail.com";
	
	private static CustomerEntity CUSTOMER_ENTITY;
	private static InsertCustomerCommand INSERT_CUSTOMER_COMMAND;
	private static InsertCustomerCommand INSERT_CUSTOMER_COMMAND_INVALID;
	
}
