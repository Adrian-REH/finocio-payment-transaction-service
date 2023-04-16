package com.finocio.finociopaymenttransactionservice.services;

import com.finocio.finociopaymenttransactionservice.entities.Payment;
import com.finocio.finociopaymenttransactionservice.exceptions.paymentExceptions.PaymentNotFoundException;
import com.finocio.finociopaymenttransactionservice.exceptions.paymentRequestExceptions.PaymentRequestAmountCeroRequestException;
import com.finocio.finociopaymenttransactionservice.exceptions.paymentRequestExceptions.PaymentRequestAmountNullRequestException;
import com.finocio.finociopaymenttransactionservice.repositories.PaymentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> findAllPayments(){

        return paymentRepository.findAll();
    }

    public ResponseEntity<?> findOnePayments(Long id){
        Optional<Payment> paymentOpt = paymentRepository.findById(id);

        if (paymentOpt.isPresent()) return ResponseEntity.ok(paymentOpt.get());
        else  throw new PaymentNotFoundException("El id no existe");

    }

    /**
     * Check si existe
     * @param payment
     * @return
     */
    public Payment savePayment(Payment payment){


        return paymentRepository.save(payment);
    }

}
