package com.sergioramos.dao;

import com.sergioramos.models.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();
    User findById(Integer id);
    boolean add(User user);
    boolean edit(User user);
    boolean deleteById(Integer id);
}
