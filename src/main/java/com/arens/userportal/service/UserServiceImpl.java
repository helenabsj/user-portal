package com.arens.userportal.service;

import com.arens.userportal.entity.User;
import com.arens.userportal.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl  implements UserService {

    private UserRepository userRepository;

    private List<User> users = new ArrayList<User>();
    public UserServiceImpl() {
    }
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {



        return userRepository.findAll();
    }


    public Optional<User> getUserById(long id) {

        Optional<User> userOptional = userRepository.findById(id);


            return userOptional;


    }


    public User saveUser(User user) {

        User u = userRepository.save(user);

        return u;
    }

    public User findByName(String username) {

        Long temp = 1L;
        User u = userRepository.findById(temp).get();

        return u;
    }

    @Override
    public User findOne(long id) {
        return null;
    }

    @Override
    public User create(User resource) {
        return null;
    }

    @Override
    public void update(User resource) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public long count() {
        return 0;
    }
}
