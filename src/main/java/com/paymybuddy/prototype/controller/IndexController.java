package com.paymybuddy.prototype.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paymybuddy.prototype.model.ContactForm;
import com.paymybuddy.prototype.validator.ContactValidator;

/*
 * Controller for /index endpoint
 */
@Controller
public class IndexController {

    @Autowired
    private ContactValidator contactValidator;

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {

	model.addAttribute("contactRegistration", new ContactForm());

	return "index";
    }

    @RequestMapping(value = "/index/contact/save", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("contactRegistration") ContactForm contactForm,
	    BindingResult bindingResult) {
	contactValidator.validate(contactForm.getReceiverEmail(), bindingResult);
	if (bindingResult.hasErrors()) {
	    return "index";
	}

	return "redirect:/index";
    }

}
