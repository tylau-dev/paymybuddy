package com.paymybuddy.prototype.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.prototype.model.Transaction;
import com.paymybuddy.prototype.repository.TransactionRepository;

@Service

public class TransactionServiceImpl implements ITransactionService {
    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
	this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public Iterable<Transaction> getTransactions() {
	return transactionRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Transaction> getTransactionById(Integer id) {
	return transactionRepository.findById(id);
    }

    @Override
    @Transactional
    public Iterable<Transaction> getCurrentUserTransaction(String email) {
	return transactionRepository.getTransactionByEmail(email);
    }

    @Override
    @Transactional
    public Transaction saveTransaction(Transaction transaction) {
	// Apply rate
	return transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public void deleteTransactionById(Integer id) {
	transactionRepository.deleteById(id);
    }

}
