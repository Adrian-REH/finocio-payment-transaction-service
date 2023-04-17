package com.finocio.finociopaymenttransactionservice.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "op_payment")
@ApiModel(description = "Un objeto que representa un pago y se guarda en BD.")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "ID único del pago generado automáticamente por la base de datos.", example = "1")
    private Long paymentId;

    @Column
    @ApiModelProperty(notes = "Fecha de creación de la transacción.", example = "dd-MM-yyyy HH:mm")
    private String date;

    @Column
    @ApiModelProperty(notes = "Cantidad de la transacción.", example = "100.50", required = true)
    private Double amount;

    @Column
    @ApiModelProperty(notes = "Identificador del usuario que realizó la transacción.", example = "123", required = true)
    private String userId;

    @Column
    @ApiModelProperty(notes = "Número de los últimos 4 dígitos de la tarjeta utilizada para el pago, generado de manera aleatoria.", example = "1234")
    private Integer cardLast4number;

    @Column
    @ApiModelProperty(notes = "Número de autorización del pago, generado de manera aleatoria.", example = "123456")
    private Integer authNumber;

    @Column
    @ApiModelProperty(notes = "Nombre del banco que procesó el pago, generado de manera aleatoria de una lista de bancos.", example = "BBVA")
    private String bank;

    @Column
    @ApiModelProperty(notes = "Indica si el pago fue realizado de manera contactless o no, generado de manera aleatoria.", example = "true")
    private Boolean isContractless;

    @Column
    @ApiModelProperty(notes = "Estado del pago, indicando si la transacción fue exitosa.", example = "PAYMENT_SUCCESS")
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
