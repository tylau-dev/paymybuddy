package com.paymybuddy.prototype.service;

import java.util.Optional;

import com.paymybuddy.prototype.model.Account;

public interface IAccountService {
    public Iterable<Account> getAccounts();

    public Optional<Account> getAccountById(Integer id);

    public Account saveAccount(Account transaction);

    public void deleteAccountById(Integer id);

}
