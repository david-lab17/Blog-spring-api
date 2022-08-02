package com.deltacode.springbootblog.repository;

import com.deltacode.springbootblog.entity.UserApp;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserApp, Long> {
    //retrieve user by email
    Optional<UserApp> findByEmail(String email);
    Optional<UserApp> findByUsernameOrEmail(String username,String email);
    Optional<UserApp> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);


}

