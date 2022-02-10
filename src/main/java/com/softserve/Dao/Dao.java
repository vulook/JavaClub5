package com.softserve.Dao;

import java.util.List;

public interface Dao<T> {
    List<T> getAll();
    T save(T t);
    T delete(long id);
    T getByID(long id);
//    T update(T t);
}
