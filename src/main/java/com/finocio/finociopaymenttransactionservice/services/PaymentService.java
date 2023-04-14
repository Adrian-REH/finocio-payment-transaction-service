package com.finocio.finociopaymenttransactionservice.services;

import com.finocio.finociopaymenttransactionservice.dto.PaymentRequest;
import com.finocio.finociopaymenttransactionservice.entities.Payment;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentService {
    Payment generateSavePayment(PaymentRequest paymentRequest);
}
