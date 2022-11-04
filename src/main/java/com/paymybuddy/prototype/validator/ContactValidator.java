package com.paymybuddy.prototype.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.paymybuddy.prototype.model.User;
import com.paymybuddy.prototype.service.IContactService;
import com.paymybuddy.prototype.service.IUserService;

@Component
public class ContactValidator implements Validator {
    @Autowired
    private IUserService userService;

    @Autowired
    private IContactService contactService;

    @Override
    public boolean supports(Class<?> clazz) {
	return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
	String receiverEmail = (String) target;

	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "receiverEmail",
		"NotEmpty.contactRegistration.receiverEmail");
//	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userRegistration.password");
//	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.userRegistration.firstName");
//	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.userRegistration.lastName");
//
//	if (!user.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
//	    errors.rejectValue("email", "Incorrect.userRegistration.email");
//	}
//
//	if (user.getPassword().length() < 6 || user.getPassword().length() > 32) {
//	    errors.rejectValue("password", "Size.userRegistration.password");
//	}
//
	Optional<User> userEmailResult = userService.getUserByEmail(receiverEmail);
	try {
	    userEmailResult.get();

	} catch (Exception e) {
	    errors.rejectValue("receiverEmail", "Nonexisting.ContactRegistration.receiverEmail");
	}

    }
}
