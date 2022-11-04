package com.paymybuddy.prototype.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.prototype.model.Account;
import com.paymybuddy.prototype.model.User;
import com.paymybuddy.prototype.repository.AccountRepository;

@Service
// Add Interface
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private IUserService userService;

    public AccountServiceImpl(AccountRepository accountRepository, IUserService userService) {
	super();
	this.accountRepository = accountRepository;
	this.userService = userService;
    }

    @Override
    @Transactional
    public Iterable<Account> getAccounts() {
	return accountRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Account> getAccountById(Integer id) {
	return accountRepository.findById(id);
    }

    @Override
    @Transactional
    public Account saveAccount(Account account) {
	return accountRepository.save(account);
    }

    @Override
    @Transactional
    public Account saveDefaultAccount(User user) {
	Account defaultAccount = new Account();
	defaultAccount.setUser(user);
	// By default and for simplification: AccountName = Lastname + Firstname
	defaultAccount.setAccountName(user.getLastName() + " " + user.getFirstName());
	defaultAccount.setBalance(0);

	return accountRepository.save(defaultAccount);
    }

    @Override
    @Transactional
    public void saveBalance(float newBalance, int accountId) {
	accountRepository.setBalanceById(newBalance, accountId);
    }

    @Override
    @Transactional
    public void deleteAccountById(Integer id) {
	accountRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Account getDefaultAccountByEmail(String email) {
	User user = userService.getUserByEmail(email).get();

	Account account = accountRepository.getAccountByUserId(user.getUserId()).get();

	return account;
    }

}
