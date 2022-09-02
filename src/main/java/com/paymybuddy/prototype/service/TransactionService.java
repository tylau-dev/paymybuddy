package com.paymybuddy.prototype.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.prototype.model.Transaction;
import com.paymybuddy.prototype.repository.TransactionRepository;

@Service

public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Iterable<Transaction> getTransactions() {
	return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Integer id) {
	return transactionRepository.findById(id);
    }

    public Transaction saveTransaction(Transaction transaction) {
	return transactionRepository.save(transaction);
    }

    public void deleteTransactionById(Integer id) {
	transactionRepository.deleteById(id);
    }

}
