package com.reading.is.good.service.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.reading.is.good.service.domain.Book;
import com.reading.is.good.service.exception.ErrorResultException;
import com.reading.is.good.service.repository.impl.BookRepositoryImpl;
import com.reading.is.good.service.repository.jpa.BookEntity;
import com.reading.is.good.service.repository.jpa.repository.BookJpaRepository;

@ExtendWith(MockitoExtension.class)
public class BookRepositoryImplTest {
	
	@InjectMocks
	private BookRepositoryImpl repository;
	
	@Mock
	private BookJpaRepository bookJpaRepository;
	
	@Test
	public void update_shouldBookData_whenBookIsExist() {
		when(bookJpaRepository.findById(ID)).thenReturn(Optional.of(BOOK_ENTITY));
		when(bookJpaRepository.save(BOOK_ENTITY)).thenReturn(BOOK_ENTITY);
		
		Book result = repository.update(ID, STOCK);
		
		assertThat(result.getId()).isEqualTo(ID);
		assertThat(result.getStock()).isEqualTo(STOCK);
		assertThat(result.getName()).isEqualTo(BOOK_NAME);
		assertThat(result.getAuthor()).isEqualTo(BOOK_AUTHOR);
	}
	
	@Test
	public void update_shouldBookData_whenBookIsNotExist() {
		when(bookJpaRepository.findById(ID)).thenReturn(Optional.empty());
		
		assertThatThrownBy(() -> repository.update(ID, STOCK)).isInstanceOf(ErrorResultException.class)
		.hasMessageContaining("Book is not exist id");
	}
	
	@BeforeAll
	public static void init() {
		BOOK_ENTITY = BookEntity.builder().id(ID).name(BOOK_NAME).author(BOOK_AUTHOR)
				.stock(CUSTOMER_ID).build();
	}
	
	
	
	private static Long ID = 12L;
	private static Long STOCK = 5L;
	private static Long CUSTOMER_ID = 123L;
	
	private static String BOOK_NAME = "BOOK_NAME";
	private static String BOOK_AUTHOR = "BOOK_AUTHOR";
	
	private static BookEntity BOOK_ENTITY;

}
