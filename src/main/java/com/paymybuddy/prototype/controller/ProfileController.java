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
import com.paymybuddy.prototype.model.BalanceForm;
import com.paymybuddy.prototype.model.Contact;
import com.paymybuddy.prototype.model.User;
import com.paymybuddy.prototype.service.IAccountService;
import com.paymybuddy.prototype.service.IContactService;
import com.paymybuddy.prototype.service.IUserService;

@Controller

public class ProfileController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IContactService contactService;

    @Autowired
    private IAccountService accountService;

    private String currentUserEmail;

    @RequestMapping(value = { "/profile" }, method = RequestMethod.GET)
    public String profile(Model model, Authentication authentication) {
	// Retrieve current user data
	this.currentUserEmail = authentication.getName();
	User currentUser = userService.getUserByEmail(currentUserEmail).get();
	model.addAttribute("userProfile", currentUser);

	// Map current User contacts for the table
	List<Contact> currentUserContacts = new ArrayList<Contact>();
	contactService.getCurrentUserContact(currentUserEmail).forEach(currentUserContacts::add);

	// Map current user balance for the form. By default user only have 1 account
	BalanceForm balanceForm = new BalanceForm();
	balanceForm.setCurrentBalance(currentUser.getAccounts().get(0).getBalance());

	model.addAttribute("contacts", currentUserContacts);
	model.addAttribute("balanceForm", balanceForm);

	return "profile";
    }

    @RequestMapping(value = "/profile/balance", method = RequestMethod.POST)
    public String topUpBalance(@ModelAttribute("balanceForm") BalanceForm balanceForm, BindingResult bindingResult) {

	Account currentAccount = accountService.getDefaultAccountByEmail(this.currentUserEmail);

	accountService.saveBalance(balanceForm.getBalancetoAdd() + currentAccount.getBalance(),
		currentAccount.getAccountId());

	return "redirect:/profile";
    }

}
