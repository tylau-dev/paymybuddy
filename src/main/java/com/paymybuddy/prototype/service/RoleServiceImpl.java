package com.paymybuddy.prototype.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.prototype.model.Role;
import com.paymybuddy.prototype.repository.RoleRepository;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
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
