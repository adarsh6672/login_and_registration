package com.usermanagement.usermanagement.service;

import com.usermanagement.usermanagement.entity.User;
import com.usermanagement.usermanagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;
    @Override
    public User saveUser(User user) {
        User newuser = userRepo.save(user);
        return newuser;
    }
}
