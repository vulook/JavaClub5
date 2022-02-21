package com.softserve.services;

import com.softserve.entity.Cart;

import java.util.List;

public interface CartService {

    Cart create(Cart book, Long id);
    //
//    Book read(Long id);
//
    Cart findByID(Long id);

    Cart delete(Long id);
    void request(Long id, Long userServiceId);
    void returnBook(Long id, Long userServiceId);
    List<Cart> getAll();
    List<Cart> findAllByID(Long id);

}
