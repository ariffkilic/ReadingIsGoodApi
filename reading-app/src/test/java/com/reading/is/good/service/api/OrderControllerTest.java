package com.reading.is.good.service.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.reading.is.good.service.api.dto.ErrorResponse;
import com.reading.is.good.service.api.dto.OrderRequest;
import com.reading.is.good.service.api.dto.OrderResponse;
import com.reading.is.good.service.constants.OrderStatus;
import com.reading.is.good.service.exception.ErrorCode;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {
	
    @LocalServerPort
    private int port;
    
    TestRestTemplate restTemplate = new TestRestTemplate();
    
    @Test
    public void addOrder_shouldCreateOrder_whenRequestIsValid() throws Exception {
    	OrderRequest entity = OrderRequest.builder().customerId(CUSTOMER_ID).bookIdList(BOOK_ID_LIST).build();
    	
    	ResponseEntity<OrderResponse> response = restTemplate.postForEntity(createURLWithPort("/api/orders"), entity, OrderResponse.class);
    	
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getCustomerId()).isEqualTo(CUSTOMER_ID);
        assertThat(response.getBody().getStatus()).isEqualTo(OrderStatus.SUCCESSFUL.toString());
    }
    
    @Test
    public void addOrder_shouldReturnErrorResponse_whenRequestIsInValid() throws Exception {
    	OrderRequest entity = OrderRequest.builder().bookIdList(BOOK_ID_LIST).build();
    	
        ResponseEntity<ErrorResponse> response = restTemplate.postForEntity(createURLWithPort("/api/orders"), entity, ErrorResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody().getType()).isEqualTo(HttpStatus.BAD_REQUEST.name());
        assertThat(response.getBody().getCode()).isEqualTo(ErrorCode.INVALID_REQUEST.name());
    }
    
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
    
    
    private Long CUSTOMER_ID = 1L;
    private List<Long> BOOK_ID_LIST = Arrays.asList(1L,2L);

}
