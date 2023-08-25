package com.usermanagement.usermanagement.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.usermanagement.usermanagement.entity.User;
import com.usermanagement.usermanagement.service.UserService;
import com.usermanagement.usermanagement.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    private UserService userservice;
    @GetMapping("/")
    public String index(){
        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }
    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @PostMapping("/saveuser")
    public String saveUser(@ModelAttribute User user){
        System.out.println(user);
        User u=userservice.saveUser(user);

        if(u!=null){
            System.out.println("successfuly saved");
        }else {
            System.out.println("something went wrong");
        }
        return "register";
    }
}