package com.softserve.Dao;

import com.softserve.entity.User;

import java.util.List;

public interface UserDao {

    User findByUserEmail(String userEmail);
    
    void save(User user);

    List<User> getAll();
}
