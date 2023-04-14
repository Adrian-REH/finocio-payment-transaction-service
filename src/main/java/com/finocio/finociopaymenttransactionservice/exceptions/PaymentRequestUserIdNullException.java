package com.finocio.finociopaymenttransactionservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PaymentRequestUserIdNullException extends ResponseStatusException {

    public PaymentRequestUserIdNullException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
