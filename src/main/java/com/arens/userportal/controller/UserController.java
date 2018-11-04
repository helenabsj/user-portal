package com.arens.userportal.controller;

import com.arens.userportal.DTO.UserDto;
import com.arens.userportal.DTO.UserMapper;
import com.arens.userportal.entity.User;
import com.arens.userportal.exception.UserNotFoundException;
import com.arens.userportal.service.UserService;
import com.arens.userportal.common.web.controller.AbstractController;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/v1")

public class UserController extends AbstractController {


    private UserService userService;

    public UserController() {
    }
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(path="users")
    public List<UserDto> getAllUsers(){
        System.out.println("in get All Users");

        List<UserDto> usersDto;

        List<User> users = userService.findAll();


            usersDto = users.stream().map(
                    user -> UserMapper.INSTANCE.userToUserDto(user)
            ).collect(Collectors.toList());

            return usersDto;

    }
    @GetMapping(path="users/{id}")
    public User getUser(@PathVariable long id) throws NotFoundException {

        if (userService.getUserById(id).isPresent()) {
            return  userService.getUserById(id).get();
        } else {
            throw new UserNotFoundException("User not found");
        }

    }

    //return status code of created
    //input - details of user
    //output - CREATED & Return the created URI
    @PostMapping(path="users")
    @ResponseStatus(HttpStatus.CREATED )
    public User createUser(@Valid @RequestBody UserDto userDto) {

        System.out.println(userDto.getFirstName());

        User u = UserMapper.INSTANCE.userDtoToUser(userDto);

        User ret = userService.saveUser(u);

        System.out.println("User has been saved - " + ret.getFirstName() + ret.getLastName());

        return ret;
    }

    @PutMapping(path="users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User udpateUser(@PathVariable Long id, @Valid @RequestBody UserDto  userDto) {

            updateInternal(id, userDto);

            System.out.println("id = " + id + "  trying to upadate user - " + userDto.getId() + userDto.getFirstName() + userDto.getLastName());

            User u = UserMapper.INSTANCE.userDtoToUser(userDto);

            User ret = userService.saveUser(u);

            return ret;

    }


    @Override
    protected UserService getService() {
        return userService;
    }
}
