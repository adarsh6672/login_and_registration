package com.usermanagement.usermanagement.repository;

import com.usermanagement.usermanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
