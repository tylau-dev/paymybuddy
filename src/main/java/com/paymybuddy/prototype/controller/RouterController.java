package com.paymybuddy.prototype.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class RouterController {

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index() {
	return "index";
    }

    // For retrieving current username
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Authentication authentication) {
	// contains username = email
	return authentication.getName();
    }

}
