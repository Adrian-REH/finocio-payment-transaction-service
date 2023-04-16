package com.finocio.finociopaymenttransactionservice.controller;

import com.finocio.finociopaymenttransactionservice.dto.PaymentRequest;
import com.finocio.finociopaymenttransactionservice.entities.Payment;
import com.finocio.finociopaymenttransactionservice.exceptions.PaymentException;
import com.finocio.finociopaymenttransactionservice.exceptions.PaymentRequestException;
import com.finocio.finociopaymenttransactionservice.exceptions.paymentRequestExceptions.PaymentRequestAmountCeroRequestException;
import com.finocio.finociopaymenttransactionservice.exceptions.paymentRequestExceptions.PaymentRequestAmountNullRequestException;
import com.finocio.finociopaymenttransactionservice.exceptions.paymentRequestExceptions.PaymentRequestUserIdBlankRequestException;
import com.finocio.finociopaymenttransactionservice.exceptions.paymentRequestExceptions.PaymentRequestUserIdNullRequestException;
import com.finocio.finociopaymenttransactionservice.services.PaymentBuilder;
import com.finocio.finociopaymenttransactionservice.services.PaymentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class PaymentController {

    private PaymentService paymentService;
    private PaymentBuilder paymentBuilder;

    public PaymentController(PaymentBuilder paymentBuilder, PaymentService paymentService ){
        this.paymentBuilder = paymentBuilder;
        this.paymentService = paymentService;
    }

    @GetMapping("/")
    @ApiOperation(value = "Busca todos los pagos", notes ="Devuelve una lista de todos los Payment creados ")
    public ResponseEntity<List<Payment>> findAllPayments(){
        return ResponseEntity.ok(paymentService.findAllPayments());
    }


    @GetMapping("/{paymentId}")
    @ApiOperation(value = "Busca un solo pago", notes ="Devuelve un Payment Object de la transacción correspondiente al identificador pasado como parámetro")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulted success", response = Payment.class),
            @ApiResponse(code = 404, message = "El id no existe", response = PaymentException.class)})
    public ResponseEntity<?> findOnePayments(@ApiParam("Clave primaria tipo Long") @PathVariable() Long paymentId){
        return paymentService.findOnePayments(paymentId);
    }


    @PostMapping("/")
    @ApiOperation(value = "Crea una nueva Transaccion de pago", notes = "Crear una transacción de pago deberá solicitar un objeto PaymentRequest con las siguientes propiedades:")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Created", response = Payment.class),
            @ApiResponse(code = 400, message = "UserID is null", response = PaymentRequestException.class),
            @ApiResponse(code = 400, message = "UserID is Blank", response = PaymentRequestException.class),
            @ApiResponse(code = 400, message = "Amount is null", response = PaymentRequestException.class),
            @ApiResponse(code = 400, message = "Amount is 0", response = PaymentRequestException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExecutionControl.class)})
    public ResponseEntity<Payment> createNewTransaction( @RequestBody PaymentRequest paymentRequest){
        return ResponseEntity.ok(paymentService.savePayment(paymentBuilder.buildPayment(paymentRequest)));
    }




}
