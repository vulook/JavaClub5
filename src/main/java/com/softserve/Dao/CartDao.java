package com.softserve.Dao;

import com.softserve.entity.Cart;

import java.util.List;

public interface CartDao {
    List<Cart> getAllByUser(Long id);

    List<Cart> getAll();

    Cart save(Cart t, Long id);

    Cart delete(long id);

    Cart getByID(long id);

    void returnBook(Long id, Long userServiceId);

    void request(long id, Long userServiceId);
}
