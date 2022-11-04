package com.paymybuddy.prototype.service;

import java.util.Optional;

import com.paymybuddy.prototype.model.Account;
import com.paymybuddy.prototype.model.User;

public interface IAccountService {
    public Iterable<Account> getAccounts();

    public Optional<Account> getAccountById(Integer id);

    public Account saveAccount(Account transaction);

    public void deleteAccountById(Integer id);

    public Account saveDefaultAccount(User user);

    public Account getDefaultAccountByEmail(String email);
}
