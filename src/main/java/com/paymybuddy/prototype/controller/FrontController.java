package com.paymybuddy.prototype.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class FrontController {

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index() {
	return "index";
    }

    @RequestMapping(value = { "/contact" }, method = RequestMethod.GET)
    public String contact() {
	return "contact";
    }

    @RequestMapping(value = { "/transfer" }, method = RequestMethod.GET)
    public String transfer() {
	return "transfer";
    }

    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String login() {
	return "login";
    }

    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
	model.addAttribute("loginError", true);
	return "login.html";
    }

}
