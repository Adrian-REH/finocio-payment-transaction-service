package com.finocio.finociopaymenttransactionservice.services;

import com.finocio.finociopaymenttransactionservice.entities.Payment;
import com.finocio.finociopaymenttransactionservice.repositories.PaymentH2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentH2Service {

    @Autowired
    private PaymentH2Repository paymentH2Repository;

    public PaymentH2Service(PaymentH2Repository paymentH2Repository) {
        this.paymentH2Repository = paymentH2Repository;
    }

    public List<Payment> findAllPayments(){

        return paymentH2Repository.findAll();
    }
    public Payment findOnePayments(Long id){
        Optional<Payment> paymentOpt = paymentH2Repository.findById(id);

        return paymentOpt.map(Payment::getPayment).orElseGet(Payment::new);
    }

    public Payment savePayment(Payment payment){
        return paymentH2Repository.save(payment);
    }
}
