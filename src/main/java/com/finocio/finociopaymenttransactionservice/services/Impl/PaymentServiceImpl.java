package com.finocio.finociopaymenttransactionservice.services.Impl;

import com.finocio.finociopaymenttransactionservice.dto.PaymentRequest;
import com.finocio.finociopaymenttransactionservice.entities.Payment;
import com.finocio.finociopaymenttransactionservice.exceptions.PaymentRequestAmountNullException;
import com.finocio.finociopaymenttransactionservice.exceptions.PaymentRequestUserIdNullException;
import com.finocio.finociopaymenttransactionservice.repositories.PaymentH2Repository;
import com.finocio.finociopaymenttransactionservice.services.PaymentH2Service;
import com.finocio.finociopaymenttransactionservice.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentH2Service paymentH2Service;

    @Autowired
    private PaymentService paymentRepository;

    /**
     * Check si ingreso parametros nulos o en blanco
     * Check si el usuario existe
     * Check si el email existe
     *
     * @param paymentRequest
     * @return Payment
     */
    @Override
    public Payment generateSavePayment(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        // Check 1: AmountIsNull
        if (paymentRequest.getAmount()==null)
            throw new PaymentRequestAmountNullException("Ingrese una clave, usuario y/ email");
        // Check 2: UserIdIsNull
        if (paymentRequest.getUserId()==null)
            throw new PaymentRequestUserIdNullException("Ingrese una clave, usuario y/ email");


        return paymentH2Service.savePayment(payment);
    }
}
