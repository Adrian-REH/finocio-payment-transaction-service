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
public class PaymentH2RepositoryDataJpaTest {

    private Payment PAYMENT_1 =  new Payment(1l,"03/03/2023",665.73 ,"2E", 8508,441,"BBVA",true,"PAYMENT_SUCCESS");
    private Payment PAYMENT_2 =  new Payment(2l,"05/03/2023",54.24, "6A", 6375,441,"Santander",true,"PAYMENT_SUCCESS");
    private Payment PAYMENT_3 =  new Payment(3l,"02/03/2023",6.13, "4D", 1545,441,"CaixaBank",false,"PAYMENT_SUCCESS");

    private final List<Payment> paymentList= new  ArrayList<Payment>(Arrays.asList(PAYMENT_1, PAYMENT_2,PAYMENT_3));

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PaymentH2Repository paymentH2Repository;

    @Test
    public void whenSavePayment_thenReturnPayment(){

        Payment payment = paymentH2Repository.save(PAYMENT_1);

        assertThat(payment.getPaymentId()) .isEqualTo(4);
        assertThat(payment.getAmount()) .isEqualTo(PAYMENT_1.getAmount());
        assertThat(payment.getUserId()) .isEqualTo(PAYMENT_1.getUserId());
        assertThat(payment.getDate()) .isEqualTo(PAYMENT_1.getDate());
        assertThat(payment.getCardLast4number()) .isEqualTo(PAYMENT_1.getCardLast4number());
        assertThat(payment.getBank()) .isEqualTo(PAYMENT_1.getBank());
        assertThat(payment.getContractless()) .isEqualTo(PAYMENT_1.getContractless());
        assertThat(payment.getStatus()) .isEqualTo(PAYMENT_1.getStatus());



    }

    @Test
    public void whenFindOnePayment_thenReturnPayment(){

        Optional<Payment> paymentOpt = paymentH2Repository.findById(1L);

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
    public void whenFindOnePayment_thenReturnPaymentList(){

        List<Payment> listPayment = paymentH2Repository.findAll();

        assertThat(listPayment.toString()) .isEqualTo(paymentList.toString());


    }

}
