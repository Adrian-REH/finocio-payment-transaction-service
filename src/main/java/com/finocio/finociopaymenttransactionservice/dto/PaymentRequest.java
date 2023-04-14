package com.finocio.finociopaymenttransactionservice.dto;


import com.finocio.finociopaymenttransactionservice.entities.Payment;

public class PaymentRequest {
    private Double amount;
    private String userId;

    public PaymentRequest(){

    }

    public PaymentRequest(Double amount, String userId) {
        this.amount = amount;
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "amount=" + amount +
                ", userId='" + userId + '\'' +
                '}';
    }
}
