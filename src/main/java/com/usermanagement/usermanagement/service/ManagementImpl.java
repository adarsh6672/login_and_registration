package com.usermanagement.usermanagement.service;

import com.usermanagement.usermanagement.entity.User;
import com.usermanagement.usermanagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

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

    @Override
    public User getUserById(int id) {
        Optional<User> optional=userRepo.findById(id);
        User user=null;
        if(optional.isPresent()){
            user=optional.get();
        }else {
            throw new RuntimeException("user not found");
        }
        return user;
    }

    @Override
    public User updateUser(User user) {
        User newuser = userRepo.save(user);
        return newuser;
    }


}
