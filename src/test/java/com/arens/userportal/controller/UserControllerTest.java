package com.arens.userportal.controller;


import com.arens.userportal.entity.User;

import com.arens.userportal.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value= UserController.class)
public class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private List<User> users = new ArrayList<>();

    @Before
    public void setUp() throws Exception {

        User user = new User("john", "doe", "jd@arens.com", "703-869-1311", "tennis");
        users.add(user);

        Optional<User> opt = Optional.of(user);

        Mockito.when(userService.findAll()).thenReturn(users);

        Mockito.when(userService.getUserById(1)).thenReturn(opt);

    }

    @Test
    public void getAllUsers() {



            try {
                RequestBuilder requestBuilder = MockMvcRequestBuilders.
                        get("/v1/users")
                        .accept(MediaType.APPLICATION_JSON);



                mockMvc.perform(requestBuilder)
                        .andExpect(jsonPath("$", hasSize(1)))
                        .andExpect(jsonPath("$[0].firstName", is(users.get(0).getFirstName())))
                        .andReturn();


            } catch (Exception e) {
                System.out.println("Excpetion" + e);
            }

    }

    @Test
    public void getUserById() {



        try {
            RequestBuilder requestBuilder = MockMvcRequestBuilders.
                    get("/v1/users/1")
                    .accept(MediaType.APPLICATION_JSON);



            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("firstName", is(users.get(0).getFirstName())))
                    .andReturn();


        } catch (Exception e) {
            System.out.println("Exception" + e);
        }

    }

    @Test
    public void getUserById_NotFound() {


        try {
            RequestBuilder requestBuilder = MockMvcRequestBuilders.
                    get("/v1/users/3")
                    .accept(MediaType.APPLICATION_JSON);



            mockMvc.perform(requestBuilder)
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("message", is("User not found")))
                    .andReturn();


        } catch (Exception e) {
            System.out.println("Excpetion" + e);
        }

    }


    /**
     * NEED TO WORK ON MY TESTS - NOT RIGHT AND THUS NOT WORKING
     */
    /*
    @Test
    public void updateUserNotNull_BadRequest() {

        try {
            RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .put("v1/users/1")
                    .accept(MediaType.APPLICATION_JSON);

            mockMvc.perform(requestBuilder)
                    .andExpect(status().isBadRequest())
                    .andReturn();

        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());
        }
    }



    @Test
    public void updateUser_BadConflict() {

        try {
            RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .put("v1/users/2")
                    .accept(MediaType.APPLICATION_JSON)
                    .param("id", "2" );

            mockMvc.perform(requestBuilder)
                    .andExpect(status().isConflict());


        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());
        }
    }
    */
}