package com.paymybuddy.prototype.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.prototype.model.Account;
import com.paymybuddy.prototype.model.User;
import com.paymybuddy.prototype.repository.AccountRepository;

@SpringBootTest
public class AccountServiceTest {
    private IAccountService accountService;

    private static Account accountToAdd = new Account(1, "Test Account", new User(), 0);

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private IUserService userService;

    @BeforeEach
    public void setUp() {
	MockitoAnnotations.initMocks(this);
	accountService = new AccountServiceImpl(accountRepository, userService);
    }

    @Test
    public void shouldGetAllAccounts() {
	List<Account> accountList = new ArrayList<>();
	accountList.add(accountToAdd);

	when(accountRepository.findAll()).thenReturn(accountList);

	List<Account> accounts = (List<Account>) accountService.getAccounts();
	assertEquals(accounts.get(0).getAccountId(), accountToAdd.getAccountId());
    }

    @Test
    public void shouldGetOneAccount() {
	when(accountRepository.findById(accountToAdd.getAccountId())).thenReturn(Optional.of(accountToAdd));

	Optional<Account> account = accountService.getAccountById(accountToAdd.getAccountId());

	assertEquals(account.get().getAccountId(), accountToAdd.getAccountId());
    }

    @Test
    public void shouldGetDefaultAccount() {
	User user = new User();
	user.setUserId(1);
	when(userService.getUserByEmail("test@email.com")).thenReturn(Optional.of(user));
	when(accountRepository.getAccountByUserId(user.getUserId())).thenReturn(Optional.of(accountToAdd));

	Account account = accountService.getDefaultAccountByEmail("test@email.com");

	assertEquals(account.getAccountId(), accountToAdd.getAccountId());
    }

    @Test
    public void shouldSaveAccount() {
	when(accountRepository.save(any(Account.class))).thenReturn(accountToAdd);

	Account accountUser = accountService.saveAccount(accountToAdd);

	assertEquals(accountUser.getAccountName(), accountToAdd.getAccountName());
    }

    @Test
    public void shouldSaveDefaultAccount() {
	when(accountRepository.save(any(Account.class))).thenReturn(accountToAdd);

	User userToAdd = new User();
	userToAdd.setFirstName("Account");
	userToAdd.setLastName("Test");

	Account defaultAccount = accountService.saveDefaultAccount(userToAdd);

	assertEquals(defaultAccount.getAccountName(), userToAdd.getLastName() + " " + userToAdd.getFirstName());
    }

    @Test
    public void shouldSaveBalance() {
	accountService.saveBalance(accountToAdd.getBalance(), accountToAdd.getAccountId());

	verify(accountRepository, times(1)).setBalanceById(accountToAdd.getBalance(), accountToAdd.getAccountId());
    }

    @Test
    public void shouldDeleteAccount() {
	accountService.deleteAccountById(accountToAdd.getAccountId());

	verify(accountRepository, times(1)).deleteById(accountToAdd.getAccountId());
    }

}
