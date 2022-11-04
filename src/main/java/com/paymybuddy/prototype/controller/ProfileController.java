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
import com.paymybuddy.prototype.model.ContactForm;
import com.paymybuddy.prototype.model.User;
import com.paymybuddy.prototype.service.IAccountService;
import com.paymybuddy.prototype.service.IContactService;
import com.paymybuddy.prototype.service.IUserService;
import com.paymybuddy.prototype.validator.ContactValidator;

@Controller

public class ProfileController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IContactService contactService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private ContactValidator contactValidator;

    private String currentUserEmail;

    @RequestMapping(value = { "/profile" }, method = RequestMethod.GET)
    public String profile(Model model, Authentication authentication) {
	this.currentUserEmail = authentication.getName();
	User currentUser = userService.getUserByEmail(currentUserEmail).get();
	model.addAttribute("userProfile", currentUser);

	List<Contact> currentUserContacts = new ArrayList<Contact>();
	contactService.getCurrentUserContact(currentUserEmail).forEach(currentUserContacts::add);

	model.addAttribute("contacts", currentUserContacts);

	model.addAttribute("connectionRegistration", new ContactForm());

	return "profile";
    }

    @RequestMapping(value = "/profile/connection/save", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("connectionRegistration") ContactForm contactForm,
	    BindingResult bindingResult) {

	contactValidator.validate(contactForm.getReceiverEmail(), bindingResult);
	if (bindingResult.hasErrors()) {
	    return "profile";
	}

	Contact contactToAdd = new Contact();

	Account senderAccount = accountService.getDefaultAccountByEmail(this.currentUserEmail);
	Account receiverAccount = accountService.getDefaultAccountByEmail(contactForm.getReceiverEmail());

	contactToAdd.setSenderAccount(senderAccount);
	contactToAdd.setReceiverAccount(receiverAccount);

	contactService.saveContact(contactToAdd);

	return "redirect:/profile";
    }
}
