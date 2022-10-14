package com.paymybuddy.prototype.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.prototype.model.Role;
import com.paymybuddy.prototype.model.User;
import com.paymybuddy.prototype.repository.RoleRepository;
import com.paymybuddy.prototype.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository) {
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
    public Optional<User> getUserByEmail(String email) {
	return userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
	Role defaultRole = roleRepository.findById(1).get();
	user.addRole(defaultRole);
	return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Integer id) {
	userRepository.deleteById(id);
    }
}