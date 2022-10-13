package com.paymybuddy.prototype.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.prototype.model.Account;
import com.paymybuddy.prototype.model.Contact;
import com.paymybuddy.prototype.repository.ContactRepository;

@SpringBootTest
public class ContactServiceUnitTest {
    private IContactService contactService;

    private static Contact contactToAdd = new Contact(1, new Account(), new Account());

    @Mock
    private ContactRepository contactRepository;

    @BeforeEach
    public void setUp() {
	MockitoAnnotations.initMocks(this);
	contactService = new ContactServiceImpl(contactRepository);
    }

    @Test
    public void shouldGetAllContacts() {
	List<Contact> contactList = new ArrayList<>();
	contactList.add(contactToAdd);

	when(contactRepository.findAll()).thenReturn(contactList);

	List<Contact> contacts = (List<Contact>) contactService.getContacts();
	assertEquals(contacts.get(0).getContactId(), contactToAdd.getContactId());
    }

    @Test
    public void shouldGetOneContact() {
	when(contactRepository.findById(contactToAdd.getContactId())).thenReturn(Optional.of(contactToAdd));

	Optional<Contact> contact = contactService.getContactById(contactToAdd.getContactId());

	assertEquals(contact.get().getContactId(), contactToAdd.getContactId());
    }

    @Test
    public void shouldSaveContact() {
	when(contactRepository.save(any(Contact.class))).thenReturn(contactToAdd);

	Contact contactUser = contactService.saveContact(contactToAdd);

	assertEquals(contactUser.getContactId(), contactToAdd.getContactId());
    }

    @Test
    public void shouldDeleteContact() {
	contactService.deleteContactById(contactToAdd.getContactId());

	verify(contactRepository, times(1)).deleteById(contactToAdd.getContactId());
    }

}
