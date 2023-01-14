package com.labs.sboot.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
public class GreetingsController {

    @RequestMapping("/greetings")
//    @ResponseBody
    public String greetings() {
        return "Welcome to Spring Boot REST!!!";
    }

    @RequestMapping("/hello")
//    @ResponseBody
    public String hello() {
        return "Hello Spring Boot REST!!!";
    }


}
