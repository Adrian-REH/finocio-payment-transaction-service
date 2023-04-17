package com.finocio.finociopaymenttransactionservice.exceptions.paymentExceptions;

import com.finocio.finociopaymenttransactionservice.exceptions.PaymentException;
import com.finocio.finociopaymenttransactionservice.exceptions.PaymentRequestException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class PaymentNotFoundException extends PaymentException {

    public PaymentNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
