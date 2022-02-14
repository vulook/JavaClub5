package com.softserve.Dao.Implementation;

import com.softserve.Dao.BookDao;
import com.softserve.entity.Author;
import com.softserve.entity.Book;
import com.softserve.entity.Cart;
import com.softserve.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Transactional
@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Book deleteCopy(long id) {
        Book book = getByID(id);
        String bookName = book.getBookName();
        Query query = sessionFactory.getCurrentSession().createSQLQuery("call DeleteOneBookCopy(:bookName)");
        query.setParameter("bookName", bookName);
        query.executeUpdate();
        return book;
    }


    @Override
    public List<Book> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Book").getResultList();
    }

    @Override
    public Book save(Book book) {
//        Author author = new Author();
//        author.setFirstName(book.getMainAuthor().getFirstName());
//        author.setLastName(book.getMainAuthor().getLastName());
//        if(sessionFactory.getCurrentSession().find(Author.class,author).getId())
        Author author = new Author();
        Query query = sessionFactory.getCurrentSession().createQuery("select a from Author a where a.firstName=:name and a.lastName=:surname");
        query.setParameter("name", book.getMainAuthor().getFirstName());
        query.setParameter("surname", book.getMainAuthor().getLastName());
        author = (Author) query.getResultList().stream().findFirst().orElse(null);
        if (author != null) {
            book.setMainAuthor(author);
//          book.setAuthorID((int) author.getId());
        } else {
            String name = (book.getMainAuthor().getFirstName());
            String surname = (book.getMainAuthor().getLastName());
            author = book.getMainAuthor();
            sessionFactory.getCurrentSession().saveOrUpdate(author);
        }
        sessionFactory.getCurrentSession().saveOrUpdate(book);
        return book;
    }

    @Override
    public Book delete(long id) {
        Book book = getByID(id);
        String bookName = book.getBookName();
        Query query = sessionFactory.getCurrentSession().createSQLQuery("call DeleteBookWithAllCopies(:bookName)");
        query.setParameter("bookName", bookName);
        query.executeUpdate();
        return book;
    }

    @Override
    public Book getByID(long id) {
        return sessionFactory.getCurrentSession().find(Book.class, id);
    }

    @Override
    public List<Book> getOwnBooks(String action) {

        User user = new User();

        Query query = sessionFactory.getCurrentSession().createSQLQuery("call getID()").addEntity(User.class);
        user = (User) query.getResultList().stream().findFirst().orElse(null);

        Query query1 = sessionFactory.getCurrentSession().createSQLQuery("call getOwnBooks(:id,:action)").addEntity(Book.class);
        query1.setParameter("id", user.getId());
        query1.setParameter("action", action);

        return query1.getResultList();
    }

    @Override
    public List<Book> FindBookByName(String name) {
        Query query1 = sessionFactory.getCurrentSession().createSQLQuery("call FindBookByTitle(:Book_Name)").addEntity(Book.class);
        query1.setParameter("Book_Name", name);
        return query1.getResultList();
    }

    @Override
    public List<Book> FindBookByAuthor(String name) {
        Query query1 = sessionFactory.getCurrentSession().createSQLQuery("call Find(:Book_Name)").addEntity(Book.class);
        query1.setParameter("Book_Name", name);
        return query1.getResultList();
    }

    @Override
    public List<Book> FindMostPopular(LocalDate start, LocalDate end) {
        Query query1 = sessionFactory.getCurrentSession().createSQLQuery("call FindMostPopular(:start,:end)").addEntity(Book.class);
        query1.setParameter("start", start);
        query1.setParameter("end", end);

        return query1.getResultList();
    }

    @Override
    public List<Book> FindLeastPopular(LocalDate start, LocalDate end) {
        Query query1 = sessionFactory.getCurrentSession().createSQLQuery("call FindLeastPopular(:start,:end)").addEntity(Book.class);
        query1.setParameter("start", start);
        query1.setParameter("end", end);

        return query1.getResultList();
    }

    @Override
    public List<Book> FindAvailable() {
        return sessionFactory.getCurrentSession().createQuery("select b from Book b where b.count > 0", Book.class).getResultList();
    }
}
