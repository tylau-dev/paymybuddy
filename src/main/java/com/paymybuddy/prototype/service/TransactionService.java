package com.paymybuddy.prototype.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.paymybuddy.prototype.model.Transaction;
import com.paymybuddy.prototype.repository.TransactionRepository;

@Service

public class TransactionService implements ITransactionService {
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
	this.transactionRepository = transactionRepository;
    }

    @Override
    public Iterable<Transaction> getTransactions() {
	return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> getTransactionById(Integer id) {
	return transactionRepository.findById(id);
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
	return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransactionById(Integer id) {
	transactionRepository.deleteById(id);
    }

}
