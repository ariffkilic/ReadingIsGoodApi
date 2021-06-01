package com.reading.is.good.service.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.reading.is.good.service.api.dto.BookRequest;
import com.reading.is.good.service.api.dto.BookResponse;
import com.reading.is.good.service.api.dto.ErrorResponse;
import com.reading.is.good.service.exception.ErrorCode;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {
	
    @LocalServerPort
    private int port;
    
    TestRestTemplate restTemplate = new TestRestTemplate();
    
    @Test
    public void addBook_shouldCreateBook_whenRequestIsValid() throws Exception {
    	BookRequest entity = BookRequest.builder().name(BOOK_NAME).author(AUTHOR_NAME).price(PRICE).stock(STOCK).build();
    	
        ResponseEntity<BookResponse> response = restTemplate.postForEntity(createURLWithPort("/api/books"), entity, BookResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getName()).isEqualTo(BOOK_NAME);
        assertThat(response.getBody().getAuthor()).isEqualTo(AUTHOR_NAME);
        assertThat(response.getBody().getPrice()).isEqualTo(PRICE);
        assertThat(response.getBody().getStock()).isEqualTo(STOCK);
    }
    
    @Test
    public void addBook_shouldReturnErrorResponse_whenRequestIsInValid() throws Exception {
    	BookRequest entity = BookRequest.builder().name(BOOK_NAME).author(AUTHOR_NAME).stock(STOCK).build();
    	
        ResponseEntity<ErrorResponse> response = restTemplate.postForEntity(createURLWithPort("/api/books"), entity, ErrorResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody().getType()).isEqualTo(HttpStatus.BAD_REQUEST.name());
        assertThat(response.getBody().getCode()).isEqualTo(ErrorCode.INVALID_REQUEST.name());
    }
    
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
    
    private static final String BOOK_NAME = "BOOK_NAME";
    private static final String AUTHOR_NAME = "AUTHOR";
    private static final BigDecimal PRICE = BigDecimal.valueOf(10);
    private static final Long STOCK = 50L;

}
