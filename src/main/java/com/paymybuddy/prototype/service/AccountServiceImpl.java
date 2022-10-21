package com.paymybuddy.prototype.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Iterable<Account> getAccounts() {
	return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccountById(Integer id) {
	return accountRepository.findById(id);
    }

    @Override
    public Account saveAccount(Account account) {
	return accountRepository.save(account);
    }

    @Override
    public Account saveDefaultAccount(User user) {
	Account defaultAccount = new Account();
	defaultAccount.setUser(user);
	defaultAccount.setAccountName("default account");
	defaultAccount.setBalance(0);

	return accountRepository.save(defaultAccount);
    }

    @Override
    public void deleteAccountById(Integer id) {
	accountRepository.deleteById(id);
    }

    @Override
    public Account getDefaultAccountByEmail(String email) {
	User user = userService.getUserByEmail(email).get();

	Account account = accountRepository.getAccountByUserId(user.getUserId()).get();

	return account;
//
//	List<Account> accountList = new ArrayList<Account>();
//	accountRepository.getAccountByEmail(email).forEach(accountList::add);
//
//	return accountList.get(0);
    }

}
