package com.finocio.finociopaymenttransactionservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PaymentRequestException extends ResponseStatusException {


    public PaymentRequestException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}
