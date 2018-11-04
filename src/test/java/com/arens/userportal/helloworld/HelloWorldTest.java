package com.arens.userportal.helloworld;

import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.awt.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value=HelloWorld.class)
public class HelloWorldTest {

        @Autowired
        private MockMvc mvc;
        @Test
        public void sayHi() {

            try {
                RequestBuilder requestBuilder = MockMvcRequestBuilders.
                        get("/api/helloworld")
                        .accept(MediaType.TEXT_PLAIN);

                String expected = "Hello World!!";
                MvcResult result = mvc.perform(requestBuilder)
                        .andExpect(status().isOk())
                        .andReturn();

                assertEquals(result.getResponse().getContentAsString(), expected);




            } catch (Exception e) {
                System.out.println("Excpetion" + e);
            }


    }

    @Test
    public void sayHiBean() {

        try {
            RequestBuilder requestBuilder = MockMvcRequestBuilders.
                    get("/api/helloworld-bean")
                    .accept(MediaType.APPLICATION_JSON);


            MvcResult result = mvc.perform(requestBuilder)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("message", is("Hello World!!")))
                    .andReturn();



        } catch (Exception e) {
            System.out.println("Excpetion" + e);
        }


    }
}