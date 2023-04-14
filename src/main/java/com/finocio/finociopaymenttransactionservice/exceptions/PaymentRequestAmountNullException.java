package com.finocio.finociopaymenttransactionservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PaymentRequestAmountNullException extends ResponseStatusException {

    public PaymentRequestAmountNullException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
