package com.softserve.dao.implementation;

import com.softserve.dao.BookDao;
import com.softserve.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

//@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Transactional
@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Book> getBooks() {
        @SuppressWarnings("unchecked")
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<Book> query = session.createQuery("from book");
        return query.getResultList();

    }
}
