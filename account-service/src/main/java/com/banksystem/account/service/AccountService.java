
package com.banksystem.account.service;

import com.banksystem.account.dto.request.CreateAccountRequest;
import com.banksystem.account.entity.Account;
import com.banksystem.account.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Account createAccount(CreateAccountRequest request) {
        Account account = new Account();

        account.setCustomerId(request.customerId());
        account.setAccountType(request.accountType());
        account.setAccountNumber(
            "ACC" + UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 20)
                .toUpperCase()
        );

        // Tài khoản mới luôn bắt đầu với số dư bằng 0.
        // Không nhận số dư từ request của client.
        return accountRepository.save(account);
    }

    @Transactional(readOnly = true)
    public Account getAccount(Long id) {
        return accountRepository.findById(id)
            .orElseThrow(() ->
                new AccountNotFoundException(
                    "Không tìm thấy tài khoản với ID: " + id
                )
            );
    }

    @Transactional(readOnly = true)
    public List<Account> getAccountsByCustomer(Long customerId) {
        return accountRepository.findByCustomerId(customerId);
    }

    public static class AccountNotFoundException
            extends RuntimeException {
        public AccountNotFoundException(String message) {
            super(message);
        }
    }
}