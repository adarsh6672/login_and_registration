package com.usermanagement.usermanagement.service;

import com.usermanagement.usermanagement.entity.User;
import com.usermanagement.usermanagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ManagementImpl implements Management{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User saveNewUser(User nuser) {

        String password=passwordEncoder.encode(nuser.getPassword());
        nuser.setPassword(password);
        nuser.setRole("ROLE_USER");
        User newnuser = userRepo.save(nuser);
        return newnuser;
    }




}
