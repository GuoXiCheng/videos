package com.videos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloController {

    @RequestMapping("/")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello Springboot";
    }
}
