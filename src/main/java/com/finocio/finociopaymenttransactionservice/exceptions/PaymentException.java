package com.finocio.finociopaymenttransactionservice.exceptions;

import io.swagger.annotations.ApiModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@ApiModel(description = "Esta clase Exception es llamada por otra cuando: No se encuentra un pago")
public class PaymentException extends ResponseStatusException {

    public PaymentException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}
