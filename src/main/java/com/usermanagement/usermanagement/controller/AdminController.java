package com.usermanagement.usermanagement.controller;

import com.usermanagement.usermanagement.entity.User;
import com.usermanagement.usermanagement.repository.UserRepo;
import com.usermanagement.usermanagement.service.Management;
import com.usermanagement.usermanagement.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private Management management;

    @Autowired
    private UserRepo userRepo;






    @GetMapping("/profile")

    public String profile(Principal p, Model m){
        if(p != null) {
            String email = p.getName();
            User user = userRepo.findByEmail(email);
            m.addAttribute("user", user);
        }
        return "admin_profile";
    }
    @GetMapping("/home")
    public String adhome(){
        return "admin_home";
    }
    @GetMapping("/management")
    public String viewData(Model model){
        model.addAttribute("userdata",management.getAllUsers());
        return "management";

    }
    @GetMapping("/userform")
    public String userForm(){
        return "newregistration";
    }
    @PostMapping("/savenewuser")
    public String saveUser(@ModelAttribute User nuser){
        userService.saveUser(nuser);

        return "redirect:management";
    }

    }

