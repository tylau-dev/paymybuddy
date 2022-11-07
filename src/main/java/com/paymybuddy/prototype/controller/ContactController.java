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
import com.paymybuddy.prototype.service.IAccountService;
import com.paymybuddy.prototype.service.IContactService;
import com.paymybuddy.prototype.validator.ContactValidator;

/*
 * Controller for /contact endpoint
 */
@Controller
public class ContactController {
    @Autowired
    private IContactService contactService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private ContactValidator contactValidator;

    private String currentUserEmail;
    private List<Contact> currentUserContacts;

    @RequestMapping(value = { "/contact" }, method = RequestMethod.GET)
    public String contact(Model model, Authentication authentication) {
	this.currentUserEmail = authentication.getName();
	this.currentUserContacts = new ArrayList<Contact>();

	// Retrieving Current User Contact
	contactService.getCurrentUserContact(currentUserEmail).forEach(this.currentUserContacts::add);

	// Adding object to Front models
	model.addAttribute("contactRegistration", new ContactForm());
	model.addAttribute("contacts", this.currentUserContacts);

	return "contact";
    }

    @RequestMapping(value = "/contact/save", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("contactRegistration") ContactForm contactForm,
	    BindingResult bindingResult, Model model) {
	// Validate form data
	contactForm.setCurrentEmail(this.currentUserEmail);
	contactValidator.validate(contactForm, bindingResult);
	if (bindingResult.hasErrors()) {
	    model.addAttribute("contacts", this.currentUserContacts);
	    return "contact";
	}

	// Creating Contact object
	Contact contactToAdd = new Contact();
	Account senderAccount = accountService.getDefaultAccountByEmail(this.currentUserEmail);
	Account receiverAccount = accountService.getDefaultAccountByEmail(contactForm.getReceiverEmail());

	contactToAdd.setSenderAccount(senderAccount);
	contactToAdd.setReceiverAccount(receiverAccount);

	contactService.saveContact(contactToAdd);

	return "redirect:/contact";
    }

}
