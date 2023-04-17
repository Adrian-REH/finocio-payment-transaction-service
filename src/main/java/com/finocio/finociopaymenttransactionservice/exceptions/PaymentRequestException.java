package com.finocio.finociopaymenttransactionservice.exceptions;

import io.swagger.annotations.ApiModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
@ApiModel(description = "Esta clase Exception es llamada por otra cuando paymentRequqest contiene: Amount Null, Amount Cero o Negativo, UserID Null, UserId Blank")
public class PaymentRequestException extends ResponseStatusException {


    public PaymentRequestException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}
