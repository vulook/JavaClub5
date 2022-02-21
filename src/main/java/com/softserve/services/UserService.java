package com.softserve.services;

import com.softserve.entity.Book;
import com.softserve.entity.User;
import com.softserve.validation.ValidationForm;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    Long getId();

    List<User> getReaders();

    List<String> getStat();

    List<User> getDebtors();

    List<Book> getStatByReader(String action, long id);

    Integer timeWithLibrary(long id);

    User findByUserEmail(String userEmail);


    void save(ValidationForm validationForm);

}
