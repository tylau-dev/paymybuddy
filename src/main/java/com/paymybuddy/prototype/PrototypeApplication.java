package com.paymybuddy.prototype;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.paymybuddy.prototype.model.User;
import com.paymybuddy.prototype.service.UserService;

@SpringBootApplication
public class PrototypeApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
	SpringApplication.run(PrototypeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

	Optional<User> optUser = userService.getUserById(1);
	User user1 = optUser.get();
	System.out.println(user1.getAccounts());

    }

}
//thymeleaf pour le front