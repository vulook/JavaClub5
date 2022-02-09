package com.softserve.services.Implementation;

import com.softserve.dao.BookDao;
import com.softserve.entity.Book;
import com.softserve.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {
    private com.softserve.dao.BookDao bookDao;
    public BookServiceImpl(BookDao b){
        this.bookDao=b;

    }
    @Transactional
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }
}
