package com.paymybuddy.prototype.service;

import java.util.Optional;

import com.paymybuddy.prototype.model.Transaction;
import com.paymybuddy.prototype.model.TransactionForm;

public interface ITransactionService {

    public Iterable<Transaction> getTransactions();

    public Optional<Transaction> getTransactionById(Integer id);

    public Iterable<Transaction> getTransactionsByMail(String email);

    public Transaction saveTransaction(Transaction transaction);

    public Transaction saveTransactionFromForm(TransactionForm transactionForm);

    public void deleteTransactionById(Integer id);
}
