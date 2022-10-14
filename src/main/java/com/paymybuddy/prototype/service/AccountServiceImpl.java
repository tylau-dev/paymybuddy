package com.paymybuddy.prototype.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.paymybuddy.prototype.model.Account;
import com.paymybuddy.prototype.repository.AccountRepository;

@Service
// Add Interface
public class AccountServiceImpl implements IAccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
	this.accountRepository = accountRepository;
    }

    @Override
    public Iterable<Account> getAccounts() {
	return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccountById(Integer id) {
	return accountRepository.findById(id);
    }

    @Override
    public Account saveAccount(Account transaction) {
	return accountRepository.save(transaction);
    }

    @Override
    public void deleteAccountById(Integer id) {
	accountRepository.deleteById(id);
    }

}
