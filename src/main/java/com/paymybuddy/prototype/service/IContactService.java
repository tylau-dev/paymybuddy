package com.paymybuddy.prototype.service;

import java.util.Optional;

import com.paymybuddy.prototype.model.Contact;

public interface IContactService {

    public Iterable<Contact> getContacts();

    public Optional<Contact> getContactById(Integer id);

    public Contact saveContact(Contact contact);

    public void deleteContactById(Integer id);

    public Iterable<Contact> getCurrentUserContact(String email);
}
