package com.paymybuddy.prototype.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.paymybuddy.prototype.model.User;
import com.paymybuddy.prototype.model.UserDetailsImpl;
import com.paymybuddy.prototype.repository.UserRepository;

/*
 * Implementation of UserDetailsService for Spring Security role based security
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	User user = userRepository.getUserByUsername(username);
	if (user == null) {
	    throw new UsernameNotFoundException("User not found");
	}
	return new UserDetailsImpl(user);
    }

}
