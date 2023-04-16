package com.finocio.finociopaymenttransactionservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PaymentAlreadyExistException extends ResponseStatusException {

    public PaymentAlreadyExistException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}

