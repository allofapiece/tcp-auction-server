package com.ock.au.service;

import com.ock.au.dao.UserDao;
import com.ock.au.entity.User;
import com.ock.au.service.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;

import java.util.HashMap;
import java.util.List;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserValidator userValidator;

    @Transactional
    @Override
    public boolean signup(User user) {
        Errors errors = new MapBindingResult(new HashMap<String, Object>(), "signupForm");
        userValidator.validate(user, errors);

        if (errors.hasErrors()) {
            return false;
        }

        userDao.create(user);

        return true;
    }

    @Transactional
    @Override
    public void create(User lot) {
        userDao.create(lot);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    public UserValidator getUserValidator() {
        return userValidator;
    }
}
