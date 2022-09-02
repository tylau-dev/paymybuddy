package com.paymybuddy.prototype.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.prototype.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

}
