package com.finocio.finociopaymenttransactionservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PaymentRequestUserIdBlankException extends ResponseStatusException {

    public PaymentRequestUserIdBlankException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
