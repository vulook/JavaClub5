package com.softserve.services;

import com.softserve.entity.User;

import java.util.List;

public interface UserService {
    void create(User user);

    void delete(Long id);

    User read(User user);

    User findById(Long id);

    List<User> findAll();


}
