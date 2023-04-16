package com.finocio.finociopaymenttransactionservice.exceptions.paymentRequestExceptions;

import com.finocio.finociopaymenttransactionservice.exceptions.PaymentRequestException;
import org.springframework.http.HttpStatus;

public class PaymentRequestAmountNullRequestException extends PaymentRequestException {

    public PaymentRequestAmountNullRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
