package com.usermanagement.usermanagement.service;

import com.usermanagement.usermanagement.entity.User;

import java.util.List;

public interface Management {
    List<User> getAllUsers();


    public User saveNewUser(User user);
}
