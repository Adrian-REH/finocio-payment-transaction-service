package com.finocio.finociopaymenttransactionservice.controller;

import com.finocio.finociopaymenttransactionservice.dto.PaymentRequest;
import com.finocio.finociopaymenttransactionservice.entities.Payment;
import com.finocio.finociopaymenttransactionservice.repositories.PaymentH2Repository;
import com.finocio.finociopaymenttransactionservice.services.PaymentH2Service;
import com.finocio.finociopaymenttransactionservice.services.PaymentService;
import com.finocio.finociopaymenttransactionservice.services.impl.PaymentServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class PaymentController {

    private PaymentH2Service paymentH2Service;
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService,PaymentH2Service paymentH2Service ){
        this.paymentService = paymentService;
        this.paymentH2Service = paymentH2Service;
    }



    @GetMapping("/")
    @ApiOperation(value = "Busca todos los pagos", notes ="Devuelve una lista de todos los Payment creados ")
    public ResponseEntity<List<Payment>> findAllPayments(){
        return ResponseEntity.ok(paymentH2Service.findAllPayments());
    }

    @GetMapping("/{paymentId}")
    @ApiOperation(value = "Busca un solo pago", notes ="Devuelve un Payment Object de la transacción correspondiente al identificador pasado como parámetro")
    public ResponseEntity<Payment> findOnePayments(@ApiParam("Clave primaria tipo Long") @PathVariable() Long paymentId){
        return paymentH2Service.findOnePayments(paymentId);
    }


    @PostMapping("/")
    @ApiOperation(value = "Crea una nueva Transaccion de pago", notes = "Crear una transacción de pago deberá solicitar un objeto PaymentRequest con las siguientes propiedades:")
    public ResponseEntity<Payment> createNewTransaction(@RequestBody PaymentRequest paymentRequest){


        return ResponseEntity.ok(paymentH2Service.savePayment(paymentService.generatePayment(paymentRequest)));
    }

}
