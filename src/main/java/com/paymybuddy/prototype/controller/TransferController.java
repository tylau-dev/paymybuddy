package com.paymybuddy.prototype.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paymybuddy.prototype.model.Account;
import com.paymybuddy.prototype.model.Contact;
import com.paymybuddy.prototype.model.Transaction;
import com.paymybuddy.prototype.model.TransactionForm;
import com.paymybuddy.prototype.service.IAccountService;
import com.paymybuddy.prototype.service.IContactService;
import com.paymybuddy.prototype.service.ITransactionService;

/*
 * Controller for /transfer endpoint
 */
@Controller
public class TransferController {
    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IContactService contactService;

    private String currentUserEmail;
    private Account currentAccount;
    private List<Contact> currentUserContacts;
    private boolean notEmptyContact;

    @RequestMapping(value = { "/transfer" }, method = RequestMethod.GET)
    public String transfer(Model model, Authentication authentication) {
	this.currentUserEmail = authentication.getName();

	// Retrieving data for transaction table
	List<Transaction> currentUserTransactions = new ArrayList<Transaction>();
	transactionService.getTransactionsByMail(currentUserEmail).forEach(currentUserTransactions::add);

	// Retrieving data for list of contact
	this.currentUserContacts = new ArrayList<Contact>();
	contactService.getCurrentUserContact(currentUserEmail).forEach(this.currentUserContacts::add);
	this.notEmptyContact = false;
	if (this.currentUserContacts.size() > 0) {
	    this.notEmptyContact = true;
	}

	// Retrieving Balance from user
	this.currentAccount = accountService.getDefaultAccountByEmail(this.currentUserEmail);
	double maxBalance = Math.floor(currentAccount.getBalance() * 0.95f);

	model.addAttribute("maxBalance", maxBalance);
	model.addAttribute("transactions", currentUserTransactions);
	model.addAttribute("currentUserContacts", this.currentUserContacts);
	model.addAttribute("notEmptyContact", this.notEmptyContact);
	model.addAttribute("transferForm", new TransactionForm());

	return "transfer";
    }

    @RequestMapping(value = "/transfer/save", method = RequestMethod.POST)
    public String registerSave(@ModelAttribute("transferForm") TransactionForm transactionForm,
	    BindingResult bindingResult) {
	// Update balance
	accountService.saveBalance(currentAccount.getBalance() - (transactionForm.getTransferredAmount() * 1.05f),
		currentAccount.getAccountId());

	transactionService.saveTransactionFromForm(transactionForm);
	return "redirect:/transfer";
    }

}
