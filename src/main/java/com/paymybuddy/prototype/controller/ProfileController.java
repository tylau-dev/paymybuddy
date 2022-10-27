package com.paymybuddy.prototype.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paymybuddy.prototype.model.User;
import com.paymybuddy.prototype.service.IUserService;

@Controller

public class ProfileController {

    @Autowired
    private IUserService userService;

    private String currentUserEmail;

    @RequestMapping(value = { "/profile" }, method = RequestMethod.GET)
    public String profile(Model model, Authentication authentication) {
	String currentUserEmail = authentication.getName();
	User currentUser = userService.getUserByEmail(currentUserEmail).get();

	model.addAttribute("userProfile", currentUser);

	return "profile";
    }
}
