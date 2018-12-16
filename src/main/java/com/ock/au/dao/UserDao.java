package com.ock.au.dao;

import com.ock.au.entity.User;

import java.util.List;

public interface UserDao {
    void create(User user);
    void delete(User user);
    List<User> findAll();
}
