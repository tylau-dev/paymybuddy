package com.paymybuddy.prototype.service;

import java.util.Optional;

import com.paymybuddy.prototype.model.User;

public interface IUserService {

    public Iterable<User> getUsers();

    public Optional<User> getUserById(Integer id);

    public User saveUser(User user);

    public void deleteUserById(Integer id);

}
