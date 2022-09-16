package com.paymybuddy.prototype.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.paymybuddy.prototype.model.Contact;
import com.paymybuddy.prototype.repository.ContactRepository;

@Service

public class ContactService implements IContactService {
    private ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
	this.contactRepository = contactRepository;
    }

    @Override
    public Iterable<Contact> getContacts() {
	return contactRepository.findAll();
    }

    @Override
    public Optional<Contact> getContactById(Integer id) {
	return contactRepository.findById(id);
    }

    @Override
    public Contact saveContact(Contact contact) {
	return contactRepository.save(contact);
    }

    @Override
    public void deleteContactById(Integer id) {
	contactRepository.deleteById(id);
    }
}
