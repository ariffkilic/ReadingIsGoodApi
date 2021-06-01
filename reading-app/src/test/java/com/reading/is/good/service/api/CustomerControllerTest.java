package com.reading.is.good.service.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.reading.is.good.service.api.dto.CustomerRequest;
import com.reading.is.good.service.api.dto.CustomerResponse;
import com.reading.is.good.service.api.dto.CustomersResponse;
import com.reading.is.good.service.api.dto.ErrorResponse;
import com.reading.is.good.service.exception.ErrorCode;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {
	
    @LocalServerPort
    private int port;
    
    TestRestTemplate restTemplate = new TestRestTemplate();
    
    @Test
    public void addCustomer_shouldCreateCustomer_whenEmailIsUnRegistered() throws Exception {
    	CustomerRequest entity = CustomerRequest.builder().name(CUSTOMER_NAME).address(CUSTOMER_ADDRESS).email(MAIL_UNREGISTERED).build();
    	
        ResponseEntity<CustomerResponse> response = restTemplate.postForEntity(createURLWithPort("/api/customers"), entity, CustomerResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getName()).isEqualTo(CUSTOMER_NAME);
        assertThat(response.getBody().getAddress()).isEqualTo(CUSTOMER_ADDRESS);
        assertThat(response.getBody().getEmail()).isEqualTo(MAIL_UNREGISTERED);
    }
    
    @Test
    public void addCustomer_shouldNotCreateCustomer_whenEmailIsRegistered() throws Exception {
    	CustomerRequest entity = CustomerRequest.builder().name(CUSTOMER_NAME).address(CUSTOMER_ADDRESS).email(MAIL_REGISTERED).build();
    	
        ResponseEntity<ErrorResponse> response = restTemplate.postForEntity(createURLWithPort("/api/customers"), entity, ErrorResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody().getType()).isEqualTo(HttpStatus.BAD_REQUEST.name());
        assertThat(response.getBody().getCode()).isEqualTo(ErrorCode.EMAIL_REGISTERED.name());
    }
    
    @Test
    public void getCustomers_shouldReturnCustomers_whenNoFail() throws Exception {
        CustomersResponse response = restTemplate.getForObject(createURLWithPort("/api/customers"), CustomersResponse.class);

        assertThat(response.getCurrentPage()).isEqualTo(1);
        assertThat(response.getTotalPages()).isEqualTo(1);
    }
    
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
    
    
    private static final String CUSTOMER_NAME = "CUSTOMER_NAME";
    private static final String CUSTOMER_ADDRESS = "ADRESS";
    private static final String MAIL_REGISTERED = "a@gmail.com";
    private static final String MAIL_UNREGISTERED = "ak@gmail.com";

}
