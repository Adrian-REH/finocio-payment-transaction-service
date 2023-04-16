package com.finocio.finociopaymenttransactionservice.repositories;

import com.finocio.finociopaymenttransactionservice.entities.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@TestPropertySource(locations = "/application-test.properties")
public class PaymentRepositoryDataJpaTest {

    private Payment PAYMENT_NEW =  new Payment(null,"14/03/2023",845.73 ,"5E", 5318,885,"BBVA",false,"PAYMENT_SUCCESS");
    private Payment PAYMENT_1 =  new Payment(1l,"03/03/2023",665.73 ,"2E", 8508,441,"BBVA",true,"PAYMENT_SUCCESS");
    private Payment PAYMENT_2 =  new Payment(2l,"05/03/2023",54.24, "6A", 6375,441,"Santander",true,"PAYMENT_SUCCESS");
    private Payment PAYMENT_3 =  new Payment(3l,"02/03/2023",6.13, "4D", 1545,441,"CaixaBank",false,"PAYMENT_SUCCESS");

    private final List<Payment> paymentList= new  ArrayList<Payment>(Arrays.asList(PAYMENT_1, PAYMENT_2,PAYMENT_3));

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    public void whenSavePayment_thenReturnPayment(){

        Payment payment = paymentRepository.save(PAYMENT_NEW);

        assertThat(payment.getPaymentId()) .isEqualTo(4L);
        assertThat(payment.getAmount()) .isEqualTo(PAYMENT_NEW.getAmount());
        assertThat(payment.getUserId()) .isEqualTo(PAYMENT_NEW.getUserId());
        assertThat(payment.getDate()) .isEqualTo(PAYMENT_NEW.getDate());
        assertThat(payment.getCardLast4number()) .isEqualTo(PAYMENT_NEW.getCardLast4number());
        assertThat(payment.getBank()) .isEqualTo(PAYMENT_NEW.getBank());
        assertThat(payment.getContractless()) .isEqualTo(PAYMENT_NEW.getContractless());
        assertThat(payment.getStatus()) .isEqualTo(PAYMENT_NEW.getStatus());



    }

    @Test
    public void whenFindOnePayment_thenReturnPayment(){

        Optional<Payment> paymentOpt = paymentRepository.findById(1L);

        if (paymentOpt.isEmpty()) assertThat(1).isEqualTo(1);

        Payment payment = paymentOpt.get();
        assertThat(payment.getPaymentId()) .isEqualTo(1L);
        assertThat(payment.getAmount()) .isEqualTo(PAYMENT_1.getAmount());
        assertThat(payment.getUserId()) .isEqualTo(PAYMENT_1.getUserId());
        assertThat(payment.getDate()) .isEqualTo(PAYMENT_1.getDate());
        assertThat(payment.getCardLast4number()) .isEqualTo(PAYMENT_1.getCardLast4number());
        assertThat(payment.getBank()) .isEqualTo(PAYMENT_1.getBank());
        assertThat(payment.getContractless()) .isEqualTo(PAYMENT_1.getContractless());
        assertThat(payment.getStatus()) .isEqualTo(PAYMENT_1.getStatus());


    }

    @Test
    public void whenFinAllPayment_thenReturnPaymentList(){

        List<Payment> listPayment = paymentRepository.findAll();

        assertThat(listPayment.toString()) .isEqualTo(paymentList.toString());


    }

}
