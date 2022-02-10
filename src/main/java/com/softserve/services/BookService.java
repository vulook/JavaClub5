package com.softserve.services;

import com.softserve.entity.Book;

import java.util.List;

public interface BookService {

    Book create(Book book);
//
//    Book read(Long id);
//
    Book findByID(Long id);

    Book delete(Long id);

    Book deleteCopy(Long id);

    List<Book>findAll();
//
//    Book findByAuthorID(Long id);
//
//    Book findByRatings(Long id);
//
//    Book findNumberOfBooks(Long id);
}
