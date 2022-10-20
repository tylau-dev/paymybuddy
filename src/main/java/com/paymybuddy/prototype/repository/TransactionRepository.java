package com.paymybuddy.prototype.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.paymybuddy.prototype.model.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    @Query(value = "WITH getUserId AS (SELECT user_id FROM user WHERE email= :email), getAccountId AS (SELECT account_id FROM account WHERE user_id IN (SELECT user_id from getUserId)), getContactId AS (SELECT contact_id FROM contact WHERE sender_account_id IN (SELECT account_id FROM getAccountId) OR receiver_account_id IN (SELECT account_id FROM getAccountId)) SELECT * FROM transaction WHERE contact_id IN (SELECT contact_id FROM getContactId);", nativeQuery = true)
    public Iterable<Transaction> getTransactionByEmail(@Param("email") String email);

}
