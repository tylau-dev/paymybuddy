package com.paymybuddy.prototype.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.paymybuddy.prototype.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    // Email is used instead of username in UserDetails implementation
    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User getUserByUsername(@Param("email") String email);

    public Optional<User> findByEmail(String email);

}
