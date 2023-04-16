package com.finocio.finociopaymenttransactionservice.services.impl;

import com.finocio.finociopaymenttransactionservice.dto.PaymentRequest;
import com.finocio.finociopaymenttransactionservice.entities.Payment;
import com.finocio.finociopaymenttransactionservice.exceptions.paymentRequestExceptions.PaymentRequestAmountCeroRequestException;
import com.finocio.finociopaymenttransactionservice.exceptions.paymentRequestExceptions.PaymentRequestAmountNullRequestException;
import com.finocio.finociopaymenttransactionservice.exceptions.paymentRequestExceptions.PaymentRequestUserIdBlankRequestException;
import com.finocio.finociopaymenttransactionservice.exceptions.paymentRequestExceptions.PaymentRequestUserIdNullRequestException;
import com.finocio.finociopaymenttransactionservice.services.PaymentBuilder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

@Service
public class PaymentBuilderImpl implements PaymentBuilder {

    /**
     * Genero el pago cumpliendo las condiciones del enunciado
     *
     * @param paymentRequest
     * @return Payment
     */
    public  Payment buildPayment(PaymentRequest paymentRequest)  {

        checkParameters(paymentRequest);

        Payment payment = new Payment();
        payment.setDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        payment.setAmount(paymentRequest.getAmount());
        payment.setUserId(paymentRequest.getUserId());
        payment.setCardLast4number(new Random().nextInt(999,9999));
        payment.setAuthNumber(new Random().nextInt(0,9999));
        payment.setBank(generateRandomBank());
        payment.setContractless(new Random().nextBoolean());
        payment.setStatus("PAYMENT_SUCCESS");

        return payment;
    }
    private void checkParameters(PaymentRequest paymentRequest){
        if (paymentRequest.getAmount()==null)
            throw new PaymentRequestAmountNullRequestException("Amount is null");

        if (paymentRequest.getUserId()==null)
            throw new PaymentRequestUserIdNullRequestException("UserID is null");

        if (paymentRequest.getAmount()<=0d)
            throw new PaymentRequestAmountCeroRequestException("Amount is menor o igual a 0");

        if (paymentRequest.getUserId().isBlank())
            throw new PaymentRequestUserIdBlankRequestException("UserID is Blank");

    }

    /**
     * Check si AmountIsNull
     * Check si UserIdIsNull
     * Check si AmountIsNull
     * Check si UserIdIsNull
     *
     * @return Payment
     */
    private String generateRandomBank(){
        ArrayList<String> bankList = new ArrayList<String>();

        bankList.add("BBVA");
        bankList.add("Santander");
        bankList.add("CaixaBank");

        Random random = new Random();
        int index = random.nextInt(bankList.size());

        return bankList.get(index);
    }


}
