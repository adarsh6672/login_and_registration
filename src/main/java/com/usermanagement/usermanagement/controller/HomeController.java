package com.usermanagement.usermanagement.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.usermanagement.usermanagement.entity.User;
import com.usermanagement.usermanagement.repository.UserRepo;
import com.usermanagement.usermanagement.service.UserService;
import com.usermanagement.usermanagement.service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    private UserServiceImpl userservice;
    @Autowired
    private UserRepo userRepo;
    @GetMapping("/")
    public String index(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }

        return "redirect:/user/home";

    }
    @GetMapping("/register")
    public String register(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "register";
        }

        return "redirect:/user/home";

    }

    @GetMapping("/login")
    public String login(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }

        return "redirect:/user/home";

    }
    @GetMapping("/user/profile")
    public String profile(Principal p, Model m){
        String email=p.getName();
        User user=userRepo.findByEmail(email);
        m.addAttribute("user",user);
        return "profile";
    }
    @GetMapping("/user/home")
    public String home(){
        return "home";
    }
    @PostMapping("/saveuser")
    public String saveUser(@ModelAttribute User user, HttpSession session){
        User u=userservice.saveUser(user);

        if(u!=null){
            System.out.println("successfuly saved");
            session.setAttribute("msg","REGISTRATION SUCCESSFUL..!");
        }else {
            System.out.println("something went wrong");
            session.setAttribute("msg","OOPS..! SOMETHING WENT WRONG");
        }
        return "redirect:/register";
    }
//    @GetMapping("/loginuser")
//    public String loginUser(){
//        return "home";
//    }
}
