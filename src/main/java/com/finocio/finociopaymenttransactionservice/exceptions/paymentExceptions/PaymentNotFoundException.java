package com.finocio.finociopaymenttransactionservice.exceptions.paymentExceptions;

import com.finocio.finociopaymenttransactionservice.exceptions.PaymentException;
import com.finocio.finociopaymenttransactionservice.exceptions.PaymentRequestException;
import org.springframework.http.HttpStatus;

public class PaymentNotFoundException extends PaymentException {

    public PaymentNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
