package com.softserve.services;

import com.softserve.entity.Book;

import java.util.List;

public interface BookService {

    Book create(Book book);

    Book read(Long id);

    void delete(Long id);

    List<Book>findAll();

    Book findByAuthorID(Long id);

    Book findByRatings(Long id);

    Book findNumberOfBooks(Long id);
}
