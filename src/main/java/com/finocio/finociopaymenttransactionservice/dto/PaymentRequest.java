package com.finocio.finociopaymenttransactionservice.dto;


import com.finocio.finociopaymenttransactionservice.entities.Payment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;

@ApiModel(description = "Un Objeto de transferencia de datos, que representa una solicitud de pago.")
public class PaymentRequest {

    @ApiModelProperty(notes = "Cantidad de la transacción.", example = "100.50", required = true)
    private Double amount;

    @ApiModelProperty(notes = "Identificador del usuario que realizó la transacción.", example = "123", required = true)
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
