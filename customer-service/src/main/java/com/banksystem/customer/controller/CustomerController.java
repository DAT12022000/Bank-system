package com.banksystem.customer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @GetMapping("/api/customers/hello")
    public String hello() {
        return "Customer Service is running!";
    }
}