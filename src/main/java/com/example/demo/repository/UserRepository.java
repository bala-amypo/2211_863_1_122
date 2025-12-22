package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Required for the unique email validation in UserServiceImpl
    Optional<User> findByEmail(String email);
    
    // Required if you implement security/login later
    Optional<User> findByUsername(String username);
}