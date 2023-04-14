package com.finocio.finociopaymenttransactionservice.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@TestPropertySource(locations = "/application-test.properties")
public class PaymentH2RepositoryDataJpaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PaymentH2Repository paymentH2Repository;

    @Test
    public void whenSavePayment_thenReturnPayment(){

    }
}
