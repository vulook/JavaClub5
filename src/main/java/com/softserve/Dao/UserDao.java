package com.softserve.Dao;

import com.softserve.entity.User;

public interface UserDao {

    User findByUserEmail(String userEmail);
    
    void save(User user);
    
}
