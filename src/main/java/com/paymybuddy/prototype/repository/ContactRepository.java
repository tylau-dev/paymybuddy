package com.paymybuddy.prototype.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.prototype.model.Contact;

@Repository

public interface ContactRepository extends CrudRepository<Contact, Integer> {

}
