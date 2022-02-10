package com.softserve.services.Implementation;

import com.softserve.Dao.Dao;
import com.softserve.Dao.Implementation.BookDaoImpl;
import com.softserve.entity.Book;
import com.softserve.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private Dao<Book> bookDao;

    @Override
    public Book create(Book book) {
        return bookDao.save(book);
    }

    @Override
    public Book findByID(Long id) {
        return bookDao.getByID(id);
    }

    @Override
    public Book delete(Long id) {
    return bookDao.delete(id);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.getAll();
    }
}
