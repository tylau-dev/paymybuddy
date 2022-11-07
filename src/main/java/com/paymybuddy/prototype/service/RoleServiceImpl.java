package com.paymybuddy.prototype.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.prototype.model.Role;
import com.paymybuddy.prototype.repository.RoleRepository;

/*
 * Service for handling Role related operations
 */
@Service
public class RoleServiceImpl implements IRoleService {
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
	this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public Role getRoleById(Integer id) {
	return roleRepository.findById(id).get();
    }

}
