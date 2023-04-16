package com.finocio.finociopaymenttransactionservice.services;

import com.finocio.finociopaymenttransactionservice.dto.PaymentRequest;
import com.finocio.finociopaymenttransactionservice.entities.Payment;


public interface PaymentBuilder {
    Payment buildPayment(PaymentRequest paymentRequest);
}
