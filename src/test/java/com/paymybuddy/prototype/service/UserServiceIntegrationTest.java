package com.paymybuddy.prototype.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.prototype.model.User;
import com.paymybuddy.prototype.repository.UserRepository;

@SpringBootTest
public class UserServiceIntegrationTest {
    @Autowired
    private UserService userService;
    private static int userTestId = 999999999;

    private static User userToAdd = new User(userTestId, "testemail@email.com", "123", "test_user", "test_user_ln_1");

    @Mock
    private UserRepository userRepository;

//    @Before
//    public void setUp
//    {
//	MockitoAnnotations.initMocks(this); // this is needed for inititalizytion of mocks, if you use @Mock
//	myDataService = new MyDataServiceImpl(myRepository);
//	myService = new MyServiceImpl(myDataService);
//
//    }

//    @BeforeAll
//    public void deleteAddedUser() {
//	List<User> users = (List<User>) userService.getUsers();
//	User lastAddedUser = users.get(users.size() - 1);
//
//	userService.deleteUserById(lastAddedUser.getUserId());
//    }

    @Test
    public void shouldGetAllUsers() {
	List<User> users = (List<User>) userService.getUsers();
	assertEquals(users.get(0).getUserId(), 1);
    }

    @Test
    public void shouldGetOneUser() {
	Optional<User> user = userService.getUserById(1);
	assertEquals(user.get().getUserId(), 1);
    }

    @Test
    public void shouldSaveUser() {
	userService.saveUser(userToAdd);
	List<User> users = (List<User>) userService.getUsers();
	User checkUser = users.get(users.size() - 1);
	int size = users.size();

	assertEquals(checkUser.getFirstName(), "test_user");
    }

    @Test
    public void shouldDeleteUser() {
	userService.saveUser(userToAdd);
	List<User> users = (List<User>) userService.getUsers();

	User lastAddedUser = users.get(users.size() - 1);
	userService.deleteUserById(lastAddedUser.getUserId());

	List<User> updatedUsers = (List<User>) userService.getUsers();
	User lastUpdatedAddedUser = updatedUsers.get(updatedUsers.size() - 1);

	assertFalse(lastUpdatedAddedUser.getFirstName() == "test_user");
    }

}
