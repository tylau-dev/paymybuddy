package com.paymybuddy.prototype.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.prototype.model.Contact;
import com.paymybuddy.prototype.model.Transaction;
import com.paymybuddy.prototype.repository.TransactionRepository;

@SpringBootTest

public class TransactionServiceTest {
    private ITransactionService transactionService;

//	this.transactionId = transactionId;
//	this.contact = contact;
//	this.description = description;
//	this.transferredAmount = transferredAmount;
//	this.paidAmount = paidAmount;
//	this.transactionDate = transactionDate;

    private static Transaction transactionToAdd = new Transaction(1, new Contact(), "transaction description", 0, 0,
	    new Date());

    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void setUp() {
	MockitoAnnotations.initMocks(this);
	transactionService = new TransactionService(transactionRepository);
    }

    @Test
    public void shouldGetAllTransactions() {
	List<Transaction> transactionList = new ArrayList<>();
	transactionList.add(transactionToAdd);

	when(transactionRepository.findAll()).thenReturn(transactionList);

	List<Transaction> transactions = (List<Transaction>) transactionService.getTransactions();
	assertEquals(transactions.get(0).getTransactionId(), transactionToAdd.getTransactionId());
    }

    @Test
    public void shouldGetOneTransaction() {
	when(transactionRepository.findById(transactionToAdd.getTransactionId()))
		.thenReturn(Optional.of(transactionToAdd));

	Optional<Transaction> transaction = transactionService.getTransactionById(transactionToAdd.getTransactionId());

	assertEquals(transaction.get().getTransactionId(), transactionToAdd.getTransactionId());
    }

    @Test
    public void shouldSaveTransaction() {
	when(transactionRepository.save(any(Transaction.class))).thenReturn(transactionToAdd);

	Transaction transactionUser = transactionService.saveTransaction(transactionToAdd);

	assertEquals(transactionUser.getTransactionId(), transactionToAdd.getTransactionId());
    }

    @Test
    public void shouldDeleteTransaction() {
	transactionService.deleteTransactionById(transactionToAdd.getTransactionId());

	verify(transactionRepository, times(1)).deleteById(transactionToAdd.getTransactionId());
    }

}
