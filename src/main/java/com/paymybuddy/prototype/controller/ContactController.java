package com.paymybuddy.prototype.controller;

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

@Controller

public class ContactController {
    @Autowired
    private IContactService contactService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private ContactValidator contactValidator;

    private String currentUserEmail;

    @RequestMapping(value = { "/contact" }, method = RequestMethod.GET)
    public String contact(Model model, Authentication authentication) {
	this.currentUserEmail = authentication.getName();

	model.addAttribute("contactRegistration", new ContactForm());

	return "contact";
    }

    @RequestMapping(value = "/contact/save", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("contactRegistration") ContactForm contactForm,
	    BindingResult bindingResult) {
	contactValidator.validate(contactForm.getReceiverEmail(), bindingResult);
	if (bindingResult.hasErrors()) {
	    return "contact";
	}

	Contact contactToAdd = new Contact();

	Account senderAccount = accountService.getDefaultAccountByEmail(this.currentUserEmail);
	Account receiverAccount = accountService.getDefaultAccountByEmail(contactForm.getReceiverEmail());

	contactToAdd.setSenderAccount(senderAccount);
	contactToAdd.setReceiverAccount(receiverAccount);

	contactService.saveContact(contactToAdd);

	return "redirect:/contact";
    }

}
