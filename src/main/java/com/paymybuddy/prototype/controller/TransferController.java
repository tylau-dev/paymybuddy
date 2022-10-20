package com.paymybuddy.prototype.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class TransferController {
    @RequestMapping(value = { "/transfer" }, method = RequestMethod.GET)
    public String transfer(Authentication authentication) {
	String currentUserEmail = authentication.getName();

	return "transfer";
    }
}
