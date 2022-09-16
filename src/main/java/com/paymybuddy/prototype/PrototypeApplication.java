package com.paymybuddy.prototype;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.paymybuddy.prototype.model.Account;
import com.paymybuddy.prototype.model.Contact;
import com.paymybuddy.prototype.model.Transaction;
import com.paymybuddy.prototype.model.User;
import com.paymybuddy.prototype.service.AccountService;
import com.paymybuddy.prototype.service.ContactService;
import com.paymybuddy.prototype.service.TransactionService;
import com.paymybuddy.prototype.service.UserService;

@SpringBootApplication
public class PrototypeApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ContactService contactService;

    public static void main(String[] args) {
	SpringApplication.run(PrototypeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

	Optional<User> optUser = userService.getUserById(1);
	User user1 = optUser.get();
	Optional<Account> optAccount = accountService.getAccountById(1);
	Account acct1 = optAccount.get();

	Optional<Contact> optcontact = contactService.getContactById(1);
	Contact testctc = optcontact.get();

	Optional<Transaction> optTrans = transactionService.getTransactionById(1);
	Transaction transact1 = optTrans.get();

	System.out.println(user1.getEmail());
	System.out.println(acct1.getUser().getEmail());
	System.out.println(transact1.getContact().getSenderAccount().getUser().getEmail());

    }

}
//thymeleaf pour le front