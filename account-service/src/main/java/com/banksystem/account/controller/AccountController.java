
package com.banksystem.account.controller;

import com.banksystem.account.dto.request.CreateAccountRequest;
import com.banksystem.account.entity.Account;
import com.banksystem.account.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(
            @Valid @RequestBody CreateAccountRequest request) {

        Account account = accountService.createAccount(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccount(id));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Account>> getAccountsByCustomer(
            @PathVariable Long customerId) {

        return ResponseEntity.ok(
            accountService.getAccountsByCustomer(customerId)
        );
    }

    @ExceptionHandler(AccountService.AccountNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(
            AccountService.AccountNotFoundException exception) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Map.of("message", exception.getMessage()));
    }
}