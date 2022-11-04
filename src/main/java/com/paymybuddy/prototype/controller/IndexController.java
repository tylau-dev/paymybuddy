package com.paymybuddy.prototype.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class IndexController {

    private boolean isRegistrationSuccessful;

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
	this.isRegistrationSuccessful = false;
	model.addAttribute("isRegistrationSuccessful", isRegistrationSuccessful);

	return "index";
    }
//
//    @RequestMapping(value = { "/index?success-register=true" }, method = RequestMethod.GET)
//    public String redirectedFromRegistration(Model model) {
//	this.isRegistrationSuccessful = true;
//	model.addAttribute("isRegistrationSuccessful", isRegistrationSuccessful);
//
//	return "index";
//    }

}
