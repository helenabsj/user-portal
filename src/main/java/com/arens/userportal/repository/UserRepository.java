package com.arens.userportal.repository;

import com.arens.userportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


    List<User> findAll();

    User findByLastName(String username);
}

