package com.paymybuddy.prototype.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paymybuddy.prototype.model.Transaction;
import com.paymybuddy.prototype.service.ITransactionService;

@Controller

public class HomeController {
    @Autowired
    private ITransactionService transactionService;

    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public String home(Model model, Authentication authentication) {
	String currentUserEmail = authentication.getName();
	List<Transaction> currentUserTransactions = new ArrayList<Transaction>();
	transactionService.getCurrentUserTransaction(currentUserEmail).forEach(currentUserTransactions::add);

	model.addAttribute("transactions", currentUserTransactions);
	return "home";
    }

}
