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
import com.paymybuddy.prototype.service.AccountServiceImpl;
import com.paymybuddy.prototype.service.ContactServiceImpl;
import com.paymybuddy.prototype.service.TransactionServiceImpl;
import com.paymybuddy.prototype.service.UserServiceImpl;

@SpringBootApplication
public class PrototypeApplication implements CommandLineRunner {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private TransactionServiceImpl transactionService;

    @Autowired
    private ContactServiceImpl contactService;

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