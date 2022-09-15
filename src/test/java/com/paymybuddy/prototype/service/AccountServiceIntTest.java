package com.paymybuddy.prototype.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.prototype.model.Account;
import com.paymybuddy.prototype.model.User;

@SpringBootTest
//@DataJpaTest
public class AccountServiceIntTest {
    @Autowired
    private AccountService accountService;
    private static int accountTestId = 999999999;

    private static User userToAdd = new User(accountTestId, "testemail@email.com", "123", "test_user_fn_1",
	    "test_user_ln_1");
    private static Account accountToAdd = new Account(accountTestId, "testAccountName", userToAdd, 0);
    private static Account accountToDelete = new Account(accountTestId, "testAccountDeleteName", userToAdd, 0);

    @Test
    public void shouldGetAllAccount() {
	List<Account> accounts = (List<Account>) accountService.getAccounts();
	assertEquals(accounts.get(0).getAccountId(), 1);
    }

    @Test
    public void shouldGetOneAccount() {
	Optional<Account> account = accountService.getAccountById(1);
	assertEquals(account.get().getAccountId(), 1);
    }

    @Test
    public void shouldSaveAccount() {
	accountService.saveAccount(accountToAdd);
	List<Account> accounts = (List<Account>) accountService.getAccounts();
	Account checkAccount = accounts.get(accounts.size() - 1);
	int size = accounts.size();
//	Optional<Account> account = accountService.getAccountById(accountTestId);

	assertEquals(checkAccount.getAccountName(), "testAccountName");
    }

    @After
    public void deleteAddedAccount() {
	List<Account> accounts = (List<Account>) accountService.getAccounts();
	Account lastAddedAccount = accounts.get(accounts.size() - 1);

	accountService.deleteAccountById(lastAddedAccount.getAccountId());
    }

    @Test
    public void shouldDeleteAccount() {
	accountService.saveAccount(accountToAdd);
	List<Account> accounts = (List<Account>) accountService.getAccounts();

	Account lastAddedAccount = accounts.get(accounts.size() - 1);
	accountService.deleteAccountById(lastAddedAccount.getAccountId());

	List<Account> updatedAccounts = (List<Account>) accountService.getAccounts();
	Account lastUpdatedAddedAccount = updatedAccounts.get(updatedAccounts.size() - 1);

	assertFalse(lastUpdatedAddedAccount.getAccountName() == "testAccountDeleteName");
    }

}
