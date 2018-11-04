package com.arens.userportal.service;

import com.arens.userportal.common.service.IService;
import com.arens.userportal.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserService extends IService<User> {

    public List<User> findAll();

    public Optional<User> getUserById(long id);

    public User saveUser(User user);

    public User findByName(String username);
}
