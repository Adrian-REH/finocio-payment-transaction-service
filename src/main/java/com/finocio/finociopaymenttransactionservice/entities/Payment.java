package com.finocio.finociopaymenttransactionservice.entities;

import io.swagger.annotations.ApiParam;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "op_payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column
    private String date;

    @Column
    private Double amount;

    @Column
    private String userId;

    @Column
    private Integer cardLast4number;

    @Column
    private Integer authNumber;

    @Column
    private String bank;

    @Column
    private Boolean isContractless;

    @Column
    private String status;

    public Payment(){

    }


    public Payment(Long paymentId, String date, Double amount, String userId, Integer cardLast4number, Integer authNumber, String bank, Boolean isContractless, String status) {
        this.paymentId = paymentId;
        this.date = date;
        this.amount = amount;
        this.userId = userId;
        this.cardLast4number = cardLast4number;
        this.authNumber = authNumber;
        this.bank = bank;
        this.isContractless = isContractless;
        this.status = status;
    }


    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public Integer getCardLast4number() {
        return cardLast4number;
    }

    public void setCardLast4number(Integer cardLast4number) {
        this.cardLast4number = cardLast4number;
    }

    public Integer getAuthNumber() {
        return authNumber;
    }

    public void setAuthNumber(Integer authNumber) {
        this.authNumber = authNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Boolean getContractless() {
        return isContractless;
    }

    public void setContractless(Boolean contractless) {
        isContractless = contractless;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", date=" + date +
                ", amount=" + amount +
                ", userId=" + userId +
                ", cardLast4number=" + cardLast4number +
                ", authNumber=" + authNumber +
                ", bank='" + bank + '\'' +
                ", isContractless=" + isContractless +
                ", status=" + status +
                '}';
    }
}
