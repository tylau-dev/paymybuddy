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
import com.paymybuddy.prototype.service.IUserService;

@Controller
public class RegisterController {
    @Autowired
    private IUserService userService;

//    @Autowired
//    private IAccountService accountService;

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
	// @todo : exception handler (existing email)
	userValidator.validate(user, bindingResult);

	if (bindingResult.hasErrors()) {
	    return "register";
	}
	user.setPassword(this.passwordEncoder.encode(user.getPassword()));
	userService.saveUser(user);
	return "redirect:/";
    }

}
