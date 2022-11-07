package com.paymybuddy.prototype.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.prototype.model.Transaction;
import com.paymybuddy.prototype.model.TransactionForm;
import com.paymybuddy.prototype.repository.TransactionRepository;

/*
 * Service for handling Transaction related operations
 */
@Service
public class TransactionServiceImpl implements ITransactionService {
    private TransactionRepository transactionRepository;
    private IContactService contactService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, IContactService contactService) {
	this.transactionRepository = transactionRepository;
	this.contactService = contactService;

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
    public Iterable<Transaction> getTransactionsByMail(String email) {
	return transactionRepository.getTransactionByEmail(email);
    }

    @Override
    @Transactional
    public Transaction saveTransaction(Transaction transaction) {
	return transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public Transaction saveTransactionFromForm(TransactionForm transactionForm) {
	Transaction transactionToAdd = new Transaction();

	transactionToAdd.setContact(contactService.getContactById(transactionForm.getContactId()).get());
	transactionToAdd.setDescription(transactionForm.getDescription());
	transactionToAdd.setTransferredAmount(transactionForm.getTransferredAmount());
	transactionToAdd.setPaidAmount(transactionForm.getTransferredAmount() * 1.05f);
	transactionToAdd.setTransactionDate(new Date());

	return transactionRepository.save(transactionToAdd);

    }

    @Override
    @Transactional
    public void deleteTransactionById(Integer id) {
	transactionRepository.deleteById(id);
    }

}
