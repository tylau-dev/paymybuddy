package com.paymybuddy.prototype.service;

import java.util.Optional;

import com.paymybuddy.prototype.model.Transaction;

public interface ITransactionService {

    public Iterable<Transaction> getTransactions();

    public Optional<Transaction> getTransactionById(Integer id);

    public Transaction saveTransaction(Transaction transaction);

    public void deleteTransactionById(Integer id);
}