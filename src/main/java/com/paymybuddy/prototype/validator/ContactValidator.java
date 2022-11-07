package com.paymybuddy.prototype.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.paymybuddy.prototype.model.Contact;
import com.paymybuddy.prototype.model.ContactForm;
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
	ContactForm contactForm = (ContactForm) target;

	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "receiverEmail",
		"NotEmpty.contactRegistration.receiverEmail");

	if (!contactForm.getReceiverEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
	    errors.rejectValue("receiverEmail", "Incorrect.contactRegistration.receiverEmail");
	}

	if (contactForm.getReceiverEmail().equals(contactForm.getCurrentEmail())) {
	    errors.rejectValue("receiverEmail", "Incorrect.existingEmail.receiverEmail");
	}

	// Check if it's an existing Email
	Optional<User> userEmailResult = userService.getUserByEmail(contactForm.getReceiverEmail());
	try {
	    userEmailResult.get();
	} catch (Exception e) {
	    errors.rejectValue("receiverEmail", "Nonexisting.contactRegistration.receiverEmail");
	}

	// Check if the contact is in the contact list
	List<Contact> currentUserContacts = new ArrayList<Contact>();
	contactService.getCurrentUserContact(contactForm.getCurrentEmail()).forEach(currentUserContacts::add);

	for (int i = 0; i < currentUserContacts.size(); i++) {
	    if (contactForm.getReceiverEmail()
		    .equals(currentUserContacts.get(i).getReceiverAccount().getUser().getEmail())) {
		errors.rejectValue("receiverEmail", "Existing.contactRegistration.receiverEmail");
		break;
	    }
	}

    }
}
