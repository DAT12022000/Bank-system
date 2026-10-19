package com.banksystem.transaction.controller;

import com.banksystem.transaction.dto.CreateTransactionRequest;
import com.banksystem.transaction.entity.Transaction;
import com.banksystem.transaction.service.TransactionService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(
            TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> create(
            @Valid @RequestBody CreateTransactionRequest request) {

        Transaction transaction = transactionService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transaction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                transactionService.getById(id));
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Transaction>> getByAccountId(
            @PathVariable Long accountId) {

        return ResponseEntity.ok(
                transactionService.getByAccountId(accountId));
    }
}