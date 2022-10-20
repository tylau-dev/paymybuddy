package com.paymybuddy.prototype.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class ContactController {
    @RequestMapping(value = { "/contact" }, method = RequestMethod.GET)
    public String contact(Authentication authentication) {
	String currentUserEmail = authentication.getName();

	return "contact";
    }

}
