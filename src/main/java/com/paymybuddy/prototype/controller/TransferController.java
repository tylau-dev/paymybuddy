package com.paymybuddy.prototype.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paymybuddy.prototype.model.Contact;
import com.paymybuddy.prototype.model.Transaction;
import com.paymybuddy.prototype.model.TransactionForm;
import com.paymybuddy.prototype.service.IContactService;
import com.paymybuddy.prototype.service.ITransactionService;

@Controller

public class TransferController {
    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private IContactService contactService;

    private String currentUserEmail;
    private List<Contact> currentUserContacts;

    @RequestMapping(value = { "/transfer" }, method = RequestMethod.GET)
    public String transfer(Model model, Authentication authentication) {
	this.currentUserEmail = authentication.getName();

	// Retrieving data for transaction table
	List<Transaction> currentUserTransactions = new ArrayList<Transaction>();
	transactionService.getCurrentUserTransaction(currentUserEmail).forEach(currentUserTransactions::add);

	model.addAttribute("transactions", currentUserTransactions);

	// Retrieving data for list of contact
	this.currentUserContacts = new ArrayList<Contact>();
	contactService.getCurrentUserContact(currentUserEmail).forEach(this.currentUserContacts::add);

	model.addAttribute("currentUserContacts", this.currentUserContacts);
	model.addAttribute("transferForm", new TransactionForm());

	return "transfer";
    }

    @RequestMapping(value = "/transfer/save", method = RequestMethod.POST)
    public String registerSave(@ModelAttribute("transferForm") TransactionForm transactionForm,
	    BindingResult bindingResult) {
	Transaction transactionToAdd = new Transaction();

	transactionToAdd.setContact(contactService.getContactById(transactionForm.getContactId()).get());
	transactionToAdd.setDescription(transactionForm.getDescription());
	transactionToAdd.setTransferredAmount(transactionForm.getTransferredAmount());
	transactionToAdd.setPaidAmount(transactionForm.getTransferredAmount() * 1.05f);
	transactionToAdd.setTransactionDate(new Date());

	transactionService.saveTransaction(transactionToAdd);

	return "redirect:/transfer";
    }

}