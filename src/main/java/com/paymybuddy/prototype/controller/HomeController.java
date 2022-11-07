package com.paymybuddy.prototype.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paymybuddy.prototype.model.User;
import com.paymybuddy.prototype.service.IUserService;

/*
 * Controller for /home endpoint
 */
@Controller
public class HomeController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public String home(Model model, Authentication authentication) {
	String currentUserEmail = authentication.getName();

	User currentUser = userService.getUserByEmail(currentUserEmail).get();

	model.addAttribute("firstName", currentUser.getFirstName());
	model.addAttribute("lastName", currentUser.getLastName());

	return "home";
    }

}
