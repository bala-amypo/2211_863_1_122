package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves a user by their email address.
     * This is used by CustomUserDetailsService for authentication 
     * and by UserServiceImpl to prevent duplicate registrations.
     */
    Optional<User> findByEmail(String email);
}