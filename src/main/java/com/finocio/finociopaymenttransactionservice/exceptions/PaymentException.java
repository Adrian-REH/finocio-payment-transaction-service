package com.finocio.finociopaymenttransactionservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PaymentException extends ResponseStatusException {

    public PaymentException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}
