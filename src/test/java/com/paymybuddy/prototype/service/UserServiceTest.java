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

import com.paymybuddy.prototype.model.User;
import com.paymybuddy.prototype.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {
    private IUserService userService;

    private static User userToAdd = new User(1, "testemail@email.com", "123", "test_user", "test_user_ln_1");

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
	MockitoAnnotations.initMocks(this);
	userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void shouldGetAllUsers() {
	List<User> userList = new ArrayList<>();
	userList.add(userToAdd);

	when(userRepository.findAll()).thenReturn(userList);

	List<User> users = (List<User>) userService.getUsers();
	assertEquals(users.get(0).getUserId(), userToAdd.getUserId());
    }

    @Test
    public void shouldGetOneUser() {
	when(userRepository.findById(userToAdd.getUserId())).thenReturn(Optional.of(userToAdd));

	Optional<User> user = userService.getUserById(userToAdd.getUserId());

	assertEquals(user.get().getUserId(), userToAdd.getUserId());
    }

    @Test
    public void shouldSaveUser() {
	when(userRepository.save(any(User.class))).thenReturn(userToAdd);

	User savedUser = userService.saveUser(userToAdd);

	assertEquals(savedUser.getFirstName(), userToAdd.getFirstName());
    }

    @Test
    public void shouldDeleteUser() {
	userService.deleteUserById(userToAdd.getUserId());

	verify(userRepository, times(1)).deleteById(userToAdd.getUserId());
    }

}
