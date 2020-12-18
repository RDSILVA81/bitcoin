package com.wyre.bitcoin.controller;

import com.wyre.bitcoin.domain.Payment;
import com.wyre.bitcoin.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_= @Autowired)
@RequestMapping("/Payments")
public class PaymentController {

    private final TransactionService service;

    @GetMapping("/All")
    @ApiOperation(value = "Return all payments received")
    public List<Payment> getAllPayment(){
       return service.getMyPayments();
    }

    @GetMapping("/Total")
    @ApiOperation(value = "Return the sum of payments received")
    public Double getSumPayment(){
        return service.getSumPayments();
    }

    @GetMapping("/MyAddress")
    @ApiOperation(value = "Return the address to use to receive bitcoin")
    public String getMyAddress(){
        return service.getMyAddress();
    }

}
