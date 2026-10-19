
package com.banksystem.transaction.service;

import com.banksystem.transaction.dto.CreateTransactionRequest;
import com.banksystem.transaction.entity.Transaction;
import com.banksystem.transaction.repository.TransactionRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(
            TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Transaction create(CreateTransactionRequest request) {

        if (request.sourceAccountId()
                .equals(request.destinationAccountId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Tai khoan nguon va tai khoan dich phai khac nhau"
            );
        }

        Transaction transaction = new Transaction();
        transaction.setSourceAccountId(request.sourceAccountId());
        transaction.setDestinationAccountId(
                request.destinationAccountId());
        transaction.setAmount(request.amount());
        transaction.setDescription(request.description());

        return transactionRepository.save(transaction);
    }

    @Transactional(readOnly = true)
    public Transaction getById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Khong tim thay giao dich voi ID: " + id
                ));
    }

    @Transactional(readOnly = true)
    public List<Transaction> getByAccountId(Long accountId) {
        return transactionRepository
                .findBySourceAccountIdOrDestinationAccountIdOrderByCreatedAtDesc(
                        accountId, accountId);
    }
}

