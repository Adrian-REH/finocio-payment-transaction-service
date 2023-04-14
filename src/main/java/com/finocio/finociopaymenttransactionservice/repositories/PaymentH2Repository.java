package com.finocio.finociopaymenttransactionservice.repositories;

import com.finocio.finociopaymenttransactionservice.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentH2Repository extends JpaRepository<Payment, Long> {

}
