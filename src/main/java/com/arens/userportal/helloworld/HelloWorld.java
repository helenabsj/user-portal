package com.arens.userportal.helloworld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class HelloWorld {

    @GetMapping(path="helloworld")
    public String sayHi(){

        return "Hello World!!";
    }

    @GetMapping(path="helloworld-bean")
    public HelloWorldBean sayHiBean(){

        return new HelloWorldBean("Hello World!!");
    }
}
