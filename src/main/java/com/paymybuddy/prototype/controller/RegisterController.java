package com.paymybuddy.prototype.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paymybuddy.prototype.model.User;
import com.paymybuddy.prototype.service.IAccountService;
import com.paymybuddy.prototype.service.IUserService;
import com.paymybuddy.prototype.validator.UserValidator;

/*
 * Controller for /register endpoint
 */
@Controller
public class RegisterController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = { "/register" }, method = RequestMethod.GET)
    public String register(Model model) {
	model.addAttribute("userRegistration", new User());
	return "register";
    }

    @RequestMapping(value = "/register/save", method = RequestMethod.POST)
    public String registerSave(@ModelAttribute("userRegistration") User user, BindingResult bindingResult) {
	// Validate form data
	userValidator.validate(user, bindingResult);
	if (bindingResult.hasErrors()) {
	    return "register";
	}
	user.setPassword(this.passwordEncoder.encode(user.getPassword()));
	userService.saveUser(user);

	// Check back database to get created user for creating default account
	User registeredUser = userService.getUserByEmail(user.getEmail()).get();
	accountService.saveDefaultAccount(registeredUser);

	// Redirect to "register successful" landing page
	return "redirect:/index?success=true";
    }

}
