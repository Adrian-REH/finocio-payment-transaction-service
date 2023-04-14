package com.finocio.finociopaymenttransactionservice.controllers;

import com.finocio.finociopaymenttransactionservice.dto.PaymentRequest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/application-test.properties")
public class PaymentControllerTest {
    private final HttpHeaders headers = new HttpHeaders();

    private final PaymentRequest paymentRequest = new PaymentRequest();

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp(){
        restTemplateBuilder = restTemplateBuilder
                .rootUri("http://localhost:"+port);
        testRestTemplate= new TestRestTemplate(restTemplateBuilder);
        headers.add("Authorization", "laptop-value-45xx23");
    }








}







