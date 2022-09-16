package com.paymybuddy.prototype.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.paymybuddy.prototype.model.User;
import com.paymybuddy.prototype.repository.UserRepository;

@Service
public class UserService implements IUserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
	this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> getUsers() {
	return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Integer id) {
	return userRepository.findById(id);
    }

    @Override
    public User saveUser(User user) {
	return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Integer id) {
	userRepository.deleteById(id);
    }
}
