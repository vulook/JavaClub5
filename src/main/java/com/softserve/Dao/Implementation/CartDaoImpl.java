package com.softserve.Dao.Implementation;

import com.softserve.Dao.CartDao;
import com.softserve.entity.Book;
import com.softserve.entity.Cart;
import com.softserve.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class CartDaoImpl implements CartDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Cart> getAllByUser(Long id) {
        List<Cart> cartList = new ArrayList<>();
        if (id != null) {
            Query query1 = sessionFactory.getCurrentSession().createQuery("select c from Cart c where c.CartUser.id=:id", Cart.class);
            query1.setParameter("id", id);
            cartList = query1.getResultList();
        }
        return cartList;
    }

    @Override
    public List<Cart> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Cart").getResultList();
    }

    @Override
    public void request(long id, Long userServiceId) {
        if (userServiceId != null) {
            Book book = sessionFactory.getCurrentSession().find(Book.class, id);
            Query query1 = sessionFactory.getCurrentSession().createSQLQuery("call RequestBook(:bookname,:uId)");
            query1.setParameter("uId", userServiceId);
            query1.setParameter("bookname", book.getBookName());
            query1.executeUpdate();
        }
    }

    @Override
    public Cart save(Cart t, Long id) {
        User user = new User();
        Query query = sessionFactory.getCurrentSession().createQuery("select u from User u where u.id=:id", User.class);
        query.setParameter("id", id);
        user = (User) query.getSingleResult();
        t.setCartUser(user);
        sessionFactory.getCurrentSession().saveOrUpdate(t);
        return t;
    }

    @Override
    public Cart delete(long id) {
        Cart cart = getByID(id);
        sessionFactory.getCurrentSession().remove(cart);
        return cart;
    }

    @Override
    public Cart getByID(long id) {
        return sessionFactory.getCurrentSession().find(Cart.class, id);
    }

    @Override
    public void returnBook(Long id, Long userServiceId) {
        if (userServiceId != null) {
            Book book = sessionFactory.getCurrentSession().find(Book.class, id);
            Query query1 = sessionFactory.getCurrentSession().createSQLQuery("call ReturnBookByUser(:bookid,:uId)");
            query1.setParameter("uId", userServiceId);
            query1.setParameter("bookid", book.getId());
            query1.executeUpdate();
        }
    }
}
