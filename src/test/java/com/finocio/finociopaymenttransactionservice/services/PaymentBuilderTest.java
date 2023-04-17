package com.finocio.finociopaymenttransactionservice.services;

import com.finocio.finociopaymenttransactionservice.dto.PaymentRequest;
import com.finocio.finociopaymenttransactionservice.entities.Payment;
import com.finocio.finociopaymenttransactionservice.exceptions.paymentRequestExceptions.PaymentRequestAmountCeroRequestException;
import com.finocio.finociopaymenttransactionservice.exceptions.paymentRequestExceptions.PaymentRequestAmountNullRequestException;
import com.finocio.finociopaymenttransactionservice.exceptions.paymentRequestExceptions.PaymentRequestUserIdBlankRequestException;
import com.finocio.finociopaymenttransactionservice.exceptions.paymentRequestExceptions.PaymentRequestUserIdNullRequestException;
import com.finocio.finociopaymenttransactionservice.services.impl.PaymentBuilderImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(Parameterized.class)
public class PaymentBuilderTest {

    public PaymentBuilder paymentBuilder;

    public PaymentBuilderTest(PaymentBuilder paymentBuilder) {
        this.paymentBuilder = paymentBuilder;
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testGeneratePaymentWithSuccess() {
        Payment payment = paymentBuilder.buildPayment(new PaymentRequest(0.2d,  "value"));

        assertNotNull(payment.getAuthNumber());
        assertNotNull(payment.getCardLast4number());
        assertNotNull(payment.getBank());
        System.out.println(payment.getDate());
    }

    @Test
    public void testGeneratePaymentWithFailAmountNegative() {
        exceptionRule.expect(PaymentRequestAmountCeroRequestException.class);
        exceptionRule.expectMessage("Amount is menor o igual a 0");

        paymentBuilder.buildPayment(new PaymentRequest(-10000.5d,  "value"));

    }

    @Test
    public void testGeneratePaymentWithFailAmountIllimit() {

        paymentBuilder.buildPayment(new PaymentRequest(9999999999999999999999999999999999999999999999999999999999999999999999d,  "value"));

    }

    @Test
    public void testGeneratePaymentWithFailAmountNull() {
        exceptionRule.expect(PaymentRequestAmountNullRequestException.class);
        exceptionRule.expectMessage("Amount is null");

        paymentBuilder.buildPayment(new PaymentRequest(null,  "value"));

    }

    @Test
    public void testGeneratePaymentWithFailAmountCero() {
        exceptionRule.expect(PaymentRequestAmountCeroRequestException.class);
        exceptionRule.expectMessage("Amount is menor o igual a 0");

        paymentBuilder.buildPayment(new PaymentRequest(0d,  "value"));

    }

    @Test
    public void testGeneratePaymentWithFailUserIdNull() {
        exceptionRule.expect(PaymentRequestUserIdNullRequestException.class);
        exceptionRule.expectMessage("UserID is null");

        paymentBuilder.buildPayment(new PaymentRequest(2.3d,  null));


    }

    @Test
    public void testGeneratePaymentWithFailUserIdBlank() {
        exceptionRule.expect(PaymentRequestUserIdBlankRequestException.class);
        exceptionRule.expectMessage("UserID is Blank");

        paymentBuilder.buildPayment(new PaymentRequest(2.3d,  ""));



    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        return Arrays.asList(
                new Object[]{new PaymentBuilderImpl()},
                new Object[]{new PaymentBuilderImpl()}
        );
    }
}
