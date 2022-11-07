package com.paymybuddy.prototype.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.prototype.model.Role;
import com.paymybuddy.prototype.model.User;
import com.paymybuddy.prototype.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IRoleService roleService;

    public UserServiceImpl(UserRepository userRepository, IRoleService roleService) {
	this.userRepository = userRepository;
	this.roleService = roleService;
    }

    @Override
    @Transactional
    public Iterable<User> getUsers() {
	return userRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<User> getUserById(Integer id) {
	return userRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<User> getUserByEmail(String email) {
	return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
	// Role "User" always have id = 1
	Role defaultRole = roleService.getRoleById(1);
	user.addRole(defaultRole);
	return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Integer id) {
	userRepository.deleteById(id);
    }
}
