package com.finocio.finociopaymenttransactionservice.controllers;

import com.finocio.finociopaymenttransactionservice.dto.PaymentRequest;
import com.finocio.finociopaymenttransactionservice.entities.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/application-test.properties")
public class PaymentControllerTest {
    private final Payment PAYMENT_1 =  new Payment(1l,"03/03/2023",665.73 ,"2E", 8508,441,"BBVA",true,"PAYMENT_SUCCESS");
    private final Payment PAYMENT_2 =  new Payment(2l,"05/03/2023",54.24, "6A", 6375,441,"Santander",true,"PAYMENT_SUCCESS");
    private final Payment PAYMENT_3 =  new Payment(3l,"02/03/2023",6.13, "4D", 1545,441,"CaixaBank",false,"PAYMENT_SUCCESS");

    private final List<Payment> paymentList= new  ArrayList<Payment>(Arrays.asList(PAYMENT_1, PAYMENT_2,PAYMENT_3));


    private TestRestTemplate testRestTemplate;
    private final HttpHeaders headers = new HttpHeaders();

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp(){
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:"+port);
        testRestTemplate= new TestRestTemplate(restTemplateBuilder);

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

    }


    @Test
    public void createNewTransactionTestFailAmountNull(){
        HttpEntity<PaymentRequest> request = new HttpEntity<>(new PaymentRequest(null,"1"),headers);
        ResponseEntity<Payment> response = testRestTemplate.postForEntity("/",request, Payment.class);

        Payment result = response.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(400, response.getStatusCodeValue());



    }

    @Test
    public void createNewTransactionTestFailAmounCero(){
        HttpEntity<PaymentRequest> request = new HttpEntity<>(new PaymentRequest(0d,"1"),headers);
        ResponseEntity<Payment> response = testRestTemplate.postForEntity("/",request, Payment.class);

        Payment result = response.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(400, response.getStatusCodeValue());
        System.out.println(result);

    }

    @Test
    public void createNewTransactionTestFailUserIdNull(){
        HttpEntity<PaymentRequest> request = new HttpEntity<>(new PaymentRequest(0.23,null),headers);
        ResponseEntity<Payment> response = testRestTemplate.postForEntity("/",request, Payment.class);

        Payment result = response.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(400, response.getStatusCodeValue());
        System.out.println(result);

    }

    @Test
    public void createNewTransactionTestFailUserIdBlank(){
        HttpEntity<PaymentRequest> request = new HttpEntity<>(new PaymentRequest(0.23,""),headers);
        ResponseEntity<Payment> response = testRestTemplate.postForEntity("/",request, Payment.class);

        Payment result = response.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(400, response.getStatusCodeValue());
        System.out.println(result);

    }

    @Test
    public void createNewDuplicateTransactionTest() {
        HttpEntity<PaymentRequest> request = new HttpEntity<>(new PaymentRequest(0.23, "1"), headers);
        ResponseEntity<Payment> responsePost1 = testRestTemplate.postForEntity("/", request, Payment.class);
        ResponseEntity<Payment> responsePost2 = testRestTemplate.postForEntity("/", request, Payment.class);

        assertEquals(HttpStatus.OK, responsePost1.getStatusCode());
        assertEquals(200, responsePost1.getStatusCodeValue());
        assertEquals(HttpStatus.OK, responsePost2.getStatusCode());
        assertEquals(200, responsePost2.getStatusCodeValue());

        Payment resultPost1 = responsePost1.getBody();
        Payment resultPost2 = responsePost2.getBody();

        assertEquals(resultPost1.getAmount(), resultPost2.getAmount());
        assertEquals(resultPost1.getUserId(), resultPost2.getUserId());
        assertEquals(resultPost1.getDate(), resultPost2.getDate());

        assertNotEquals(resultPost1.getCardLast4number(), resultPost2.getCardLast4number());
        assertNotEquals(resultPost1.getAuthNumber(), resultPost2.getAuthNumber());
        //assertNotEquals(resultPost1.getBank(), resultPost2.getBank()); // Tiene un 33% de probabilidad de fallar. Solo hay 3 bancos
        //assertNotEquals(resultPost1.getContractless(), resultPost2.getContractless()); // Tiene un 50% de probabilidad de fallar. Solo hay true o false
        assertEquals(resultPost1.getStatus(), resultPost2.getStatus());


    }

    /**
     * Check si la peticion salio OK
     * Check si el Body cumple con el enunciado
     * Check si el id del body existe en h2 y tiene los mismos valores
     *
     */
    @Test
    public void createNewTransactionTest(){
        HttpEntity<PaymentRequest> request = new HttpEntity<>(new PaymentRequest(0.23,"1"),headers);
        ResponseEntity<Payment> responsePost = testRestTemplate.postForEntity("/",request, Payment.class);

        assertEquals(HttpStatus.OK, responsePost.getStatusCode());
        assertEquals(200, responsePost.getStatusCodeValue());

        Payment resultPost = responsePost.getBody();

        assertEquals(0.23d, resultPost.getAmount());
        assertEquals("1", resultPost.getUserId());
        assertTrue(containBankList(resultPost.getBank()) );
        assertEquals(4,cuentaCifras(resultPost.getCardLast4number()));
        assertEquals("PAYMENT_SUCCESS", resultPost.getStatus());


        ResponseEntity<Payment> responseGet = testRestTemplate.getForEntity("/"+ resultPost.getPaymentId(), Payment.class);
        Payment resultGet = responseGet.getBody();
        assertEquals(HttpStatus.OK, responseGet.getStatusCode());
        assertEquals(200, responseGet.getStatusCodeValue());

        assertEquals(resultGet.toString(), resultPost.toString());


    }

    /**
     * Cehck si la peticion es OK
     * Cehck si me da una lista con las instancias creadas
     * check si la lista contiene los campos requeridos
     */
    @Test
    public void findAllPaymentsTest(){
        ResponseEntity<Payment[]> response = testRestTemplate.getForEntity("/",Payment[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        List<Payment> payments=new  ArrayList<>();

        for (int i = 0; i < 3; i++) {
            payments.add(Objects.requireNonNull(response.getBody())[i]);

        }

        assertEquals(paymentList.toString(), payments.toString());



    }

    /**
     * check si la peticion es OK
     * check si la peticion contiene los campos requeridos
     */
    @Test
    public void findOnePaymentsTest(){
        ResponseEntity<Payment> response = testRestTemplate.getForEntity("/1", Payment.class);

        Payment result = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        assertEquals(PAYMENT_1.toString(), result.toString());


    }

    private boolean containBankList(String bank){
        ArrayList<String> banks = new ArrayList<>();
        banks.add("BBVA");
        banks.add("Santander");
        banks.add("CaixaBank");
        return banks.contains(bank);
    }

    private int cuentaCifras(int num) {
        // Si el numero es 0, tiene una cifra
        int contador =(num==0)? 1:0;

        if (num != 0) {
            // Vamos dividiendo entre 10 hasta que lleguemos a 0
            for (int i = Math.abs(num); i > 0; i /= 10) {
                contador++;
            }

        }

        return contador;
    }

    @Test
    public void findOnePaymentsTestFailNotFound(){
        ResponseEntity<Payment> response = testRestTemplate.getForEntity("/100", Payment.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(404, response.getStatusCodeValue());


    }








}







