package com.paymybuddy.prototype.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.prototype.model.Contact;
import com.paymybuddy.prototype.repository.ContactRepository;

@Service

public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public Iterable<Contact> getContacts() {
	return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(Integer id) {
	return contactRepository.findById(id);
    }

    public Contact saveContact(Contact contact) {
	return contactRepository.save(contact);
    }

    public void deleteContactById(Integer id) {
	contactRepository.deleteById(id);
    }
}
