package com.paymybuddy.prototype.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.paymybuddy.prototype.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    @Query(value = "SELECT * FROM Account a WHERE a.user_id = :userId", nativeQuery = true)
    public Optional<Account> getAccountByUserId(@Param("userId") int userId);

    @Query(value = "WITH getUserId AS (SELECT user_id FROM user WHERE email= :email) SELECT * FROM account WHERE user_id IN (SELECT user_id FROM getUserId);", nativeQuery = true)
    public Iterable<Account> getAccountByEmail(@Param("email") String email);

}
