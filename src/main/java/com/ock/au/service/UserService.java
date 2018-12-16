package com.ock.au.service;

import com.ock.au.entity.User;
import com.ock.au.service.validator.UserValidator;

import java.util.List;

public interface UserService {
    boolean signup(User user);
    void create(User user);
    List<User> findAll();
    UserValidator getUserValidator();
}
