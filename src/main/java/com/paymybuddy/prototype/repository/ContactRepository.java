package com.paymybuddy.prototype.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.paymybuddy.prototype.model.Contact;

@Repository

public interface ContactRepository extends CrudRepository<Contact, Integer> {

    @Query(value = "WITH getUserId AS (select user_id FROM user where email= :email), getAccountId AS (SELECT account_id FROM account WHERE user_id IN (SELECT * from getUserId)) select * from contact where sender_account_id IN (SELECT * from getAccountId)", nativeQuery = true)
    public Iterable<Contact> getContactByEmail(@Param("email") String email);

}
