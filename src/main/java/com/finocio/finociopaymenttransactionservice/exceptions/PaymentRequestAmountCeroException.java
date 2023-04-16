package com.finocio.finociopaymenttransactionservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PaymentRequestAmountCeroException extends ResponseStatusException {

    public PaymentRequestAmountCeroException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
