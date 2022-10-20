package com.paymybuddy.prototype.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paymybuddy.prototype.service.IUserService;

@Controller
public class LoginController {
    @Autowired
    private IUserService userService;

    // @Todo security service for automatic login???

//    @Autowired
//    private IAccountService accountService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String login(Model model, String error) {
	if (error != null) {
	    model.addAttribute("error", "Invalid Email or Password");
	}

	return "login";
    }

    @RequestMapping("/login-error.html")
    public String loginError(Model model, String error) {
	model.addAttribute("loginError", true);
	return "login.html";
    }
}
