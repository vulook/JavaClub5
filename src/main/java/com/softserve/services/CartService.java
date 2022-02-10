package com.softserve.services;

import com.softserve.entity.Book;
import com.softserve.entity.Cart;

import java.util.List;

public interface CartService {

    Cart create(Cart book);
    //
//    Book read(Long id);
//
    Cart findByID(Long id);

    Cart delete(Long id);
    void request(Long id);

    List<Cart> findAll();

}
