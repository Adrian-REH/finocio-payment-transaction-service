package com.finocio.finociopaymenttransactionservice.services.Impl;

import com.finocio.finociopaymenttransactionservice.dto.PaymentRequest;
import com.finocio.finociopaymenttransactionservice.entities.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentServiceImplTest {

    @Autowired
    private PaymentServiceImpl paymentServiceImpl;

    @Test
    void testGenerateSavePaymentWithSuccess() {

    }
    @Test
    void testGenerateSavePaymentWithFail_AmountNull() {
        Payment payment = paymentServiceImpl.generateSavePayment(new PaymentRequest());

    }
    @Test
    void testGenerateSavePaymentWithFail_UserIdNull() {

        Payment payment = paymentServiceImpl.generateSavePayment(new PaymentRequest());


    }

    private PaymentRequest paymentGenerator(){
        new Payment(null, Date.from(Instant.now()),
              12.22,
              22L,
              123,
              23,
              "SD",
              true,
              "PAYMENT_SUCCESS");
      return  new PaymentRequest(22.3d,
              "23s");
    }


}