package com.paymybuddy.prototype.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.prototype.model.Account;
import com.paymybuddy.prototype.repository.AccountRepository;

@Service

public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Iterable<Account> getAccounts() {
	return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(Integer id) {
	return accountRepository.findById(id);
    }

    public Account saveAccount(Account transaction) {
	return accountRepository.save(transaction);
    }

    public void deleteAccountById(Integer id) {
	accountRepository.deleteById(id);
    }

}
