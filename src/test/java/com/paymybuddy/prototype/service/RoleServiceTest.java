package com.paymybuddy.prototype.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.prototype.model.Role;
import com.paymybuddy.prototype.repository.RoleRepository;

@SpringBootTest
public class RoleServiceTest {
    private IRoleService roleService;

    @Mock
    private RoleRepository roleRepository;

    private static Role roleMock = new Role(0, "user");

    @BeforeEach
    public void setUp() {
	MockitoAnnotations.initMocks(this);

	roleService = new RoleServiceImpl(roleRepository);
    }

    @Test
    public void shouldGetRole() {
	when(roleRepository.findById(0)).thenReturn(Optional.of(roleMock));

	Role role = roleService.getRoleById(roleMock.getId());

	assertEquals(role.getId(), roleMock.getId());
    }

}
