package com.softserve.services.Implementation;

import com.softserve.Dao.BookDao;
import com.softserve.entity.Book;
import com.softserve.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book create(Book book) {
        return bookDao.save(book);
    }

    @Override
    public Book deleteCopy(Long id) {
        return bookDao.deleteCopy(id);
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
