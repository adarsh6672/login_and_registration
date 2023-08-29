package com.usermanagement.usermanagement.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.usermanagement.usermanagement.entity.User;
import com.usermanagement.usermanagement.repository.UserRepo;
import com.usermanagement.usermanagement.service.UserService;
import com.usermanagement.usermanagement.service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    private UserServiceImpl userservice;
//    @Autowired
//    private UserRepo userRepo;


    @GetMapping("/")
    @Secured({"ROLE_ADMIN", "ROLE_USER"}) // Define the roles that can access this endpoint
    public String log() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login"; // If not authenticated, show the login page.
        }

        // Check user roles and redirect accordingly.
        if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/admin/home"; // Redirect admins to the admin home page.
        } else if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"))) {
            return "redirect:/user/home"; // Redirect regular users to the user home page.
        }

        // Redirect to a default page if no matching role is found.
        return "redirect:/default/home";
    }
//    @GetMapping("/")
//    public String index(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
//            return "login";
//        }
//
//        return "redirect/admin/home:";
//
//    }

    @GetMapping("/register")
    @Secured({"ROLE_ADMIN", "ROLE_USER"}) // Define the roles that can access this endpoint
    public String register() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "register"; // If not authenticated, show the login page.
        }

        // Check user roles and redirect accordingly.
        if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/admin/home"; // Redirect admins to the admin home page.
        } else if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"))) {
            return "redirect:/user/home"; // Redirect regular users to the user home page.
        }

        // Redirect to a default page if no matching role is found.
        return "redirect:/default/home";
    }

//    @PostMapping("/registere")
//    public String registerUser(@Valid User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "register";
//        }
//
//        // Process user registration
//
//        return "redirect:/";
//    }
//    @GetMapping("/register")
//    public String register(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
//            return "register";
//        }
//
//        return "redirect:/admin/home";
//
//    }


    @GetMapping("/login")
    @Secured({"ROLE_ADMIN", "ROLE_USER"}) // Define the roles that can access this endpoint
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login"; // If not authenticated, show the login page.
        }

        // Check user roles and redirect accordingly.
        if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/admin/home"; // Redirect admins to the admin home page.
        } else if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"))) {
            return "redirect:/user/home"; // Redirect regular users to the user home page.
        }

        // Redirect to a default page if no matching role is found.
        return "redirect:/default/home";
    }


    //    @GetMapping("/login")
//    public String login(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
//            return "login";
//        }
//
//        return "redirect:/admin/home";
//
//    }
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


}
