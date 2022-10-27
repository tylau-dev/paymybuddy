package com.paymybuddy.prototype.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.prototype.model.Contact;
import com.paymybuddy.prototype.repository.ContactRepository;

@Service

public class ContactServiceImpl implements IContactService {
    private ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
	this.contactRepository = contactRepository;
    }

    @Override
    @Transactional
    public Iterable<Contact> getContacts() {
	return contactRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Contact> getContactById(Integer id) {
	return contactRepository.findById(id);
    }

    @Override
    @Transactional
    public Contact saveContact(Contact contact) {
	return contactRepository.save(contact);
    }

    @Override
    @Transactional
    public void deleteContactById(Integer id) {
	contactRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Iterable<Contact> getCurrentUserContact(String email) {
	return contactRepository.getContactByEmail(email);
    }
}
