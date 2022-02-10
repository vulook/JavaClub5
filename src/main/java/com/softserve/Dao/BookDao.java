package com.softserve.Dao;

import com.softserve.entity.Book;

import java.util.List;

public interface BookDao {
    List<Book> getAll();
    Book save(Book t);
    Book delete(long id);
    Book deleteCopy(long id);
    Book getByID(long id);
//    T update(T t);
}
