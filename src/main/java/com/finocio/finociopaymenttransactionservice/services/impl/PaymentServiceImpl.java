package com.finocio.finociopaymenttransactionservice.services.impl;

import com.finocio.finociopaymenttransactionservice.dto.PaymentRequest;
import com.finocio.finociopaymenttransactionservice.entities.Payment;
import com.finocio.finociopaymenttransactionservice.exceptions.PaymentRequestAmountCeroException;
import com.finocio.finociopaymenttransactionservice.exceptions.PaymentRequestAmountNullException;
import com.finocio.finociopaymenttransactionservice.exceptions.PaymentRequestUserIdBlankException;
import com.finocio.finociopaymenttransactionservice.exceptions.PaymentRequestUserIdNullException;
import com.finocio.finociopaymenttransactionservice.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import static javax.xml.bind.DatatypeConverter.parseInt;

@Service
public class PaymentServiceImpl implements PaymentService {

    /**
     * Check si AmountIsNull
     * Check si UserIdIsNull
     * Check si AmountIsNull
     * Check si UserIdIsNull
     *
     * @param paymentRequest
     * @return Payment
     */
    public  Payment generatePayment(PaymentRequest paymentRequest)  {

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
            throw new PaymentRequestAmountNullException("Amount is null");

        if (paymentRequest.getUserId()==null)
            throw new PaymentRequestUserIdNullException("UserID is null");

        if (paymentRequest.getAmount()==0d)
            throw new PaymentRequestAmountCeroException("Amount is 0");

        if (paymentRequest.getUserId().isBlank())
            throw new PaymentRequestUserIdBlankException("UserID is Blank");

    }

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
