package com.paymybuddy.prototype.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.prototype.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
