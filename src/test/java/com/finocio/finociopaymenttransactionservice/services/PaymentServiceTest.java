package com.finocio.finociopaymenttransactionservice.services;

import com.finocio.finociopaymenttransactionservice.dto.PaymentRequest;
import com.finocio.finociopaymenttransactionservice.entities.Payment;
import com.finocio.finociopaymenttransactionservice.exceptions.PaymentRequestAmountCeroException;
import com.finocio.finociopaymenttransactionservice.exceptions.PaymentRequestAmountNullException;
import com.finocio.finociopaymenttransactionservice.exceptions.PaymentRequestUserIdBlankException;
import com.finocio.finociopaymenttransactionservice.exceptions.PaymentRequestUserIdNullException;
import com.finocio.finociopaymenttransactionservice.services.impl.PaymentServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(Parameterized.class)
public class PaymentServiceTest {

    public PaymentService paymentService;
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    public PaymentServiceTest(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @Test
    public void testGenerateSavePaymentWithSuccess() {
        Payment payment = paymentService.generatePayment(new PaymentRequest(0.2d,  "value"));

        assertNotNull(payment.getAuthNumber());
        assertNotNull(payment.getCardLast4number());
        assertNotNull(payment.getBank());
    }

    @Test
    public void testGenerateSavePaymentWithFailAmountNull() {
        exceptionRule.expect(PaymentRequestAmountNullException.class);
        exceptionRule.expectMessage("Amount is null");

        paymentService.generatePayment(new PaymentRequest(null,  "value"));

    }

    @Test
    public void testGenerateSavePaymentWithFailAmountCero() {
        exceptionRule.expect(PaymentRequestAmountCeroException.class);
        exceptionRule.expectMessage("Amount is 0");

        paymentService.generatePayment(new PaymentRequest(0d,  "value"));

    }

    @Test
    public void testGenerateSavePaymentWithFailUserIdNull() {
        exceptionRule.expect(PaymentRequestUserIdNullException.class);
        exceptionRule.expectMessage("UserID is null");

        paymentService.generatePayment(new PaymentRequest(2.3d,  null));


    }

    @Test
    public void testGenerateSavePaymentWithFailUserIdBlank() {
        exceptionRule.expect(PaymentRequestUserIdBlankException.class);
        exceptionRule.expectMessage("UserID is Blank");

        paymentService.generatePayment(new PaymentRequest(2.3d,  ""));



    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        return Arrays.asList(
                new Object[]{new PaymentServiceImpl()},
                new Object[]{new PaymentServiceImpl()}
        );
    }
}
