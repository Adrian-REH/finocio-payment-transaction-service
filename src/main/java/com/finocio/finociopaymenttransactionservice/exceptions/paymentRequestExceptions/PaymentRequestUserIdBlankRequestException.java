package com.finocio.finociopaymenttransactionservice.exceptions.paymentRequestExceptions;

import com.finocio.finociopaymenttransactionservice.exceptions.PaymentRequestException;
import org.springframework.http.HttpStatus;

public class PaymentRequestUserIdBlankRequestException extends PaymentRequestException {

    public PaymentRequestUserIdBlankRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
