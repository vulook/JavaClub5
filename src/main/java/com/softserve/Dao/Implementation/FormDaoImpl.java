package com.softserve.Dao.Implementation;

import com.softserve.Dao.FormDao;
import com.softserve.entity.Form;
import com.softserve.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class FormDaoImpl implements FormDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Form> getAllByUser() {
        User user = new User();

        Query query = sessionFactory.getCurrentSession().createSQLQuery("call getID()").addEntity(User.class);
        user = (User) query.getResultList().stream().findFirst().orElse(null);

        Query query1 = sessionFactory.getCurrentSession().createQuery("select c from Form c where c.FormUser.id=:id", Form.class);
        query1.setParameter("id", user.getId());

        return query1.getResultList();
    }

    @Override
    public List<Form> getAll() {
        return sessionFactory.getCurrentSession().createQuery("select f from Form f").getResultList();
    }

    @Override
    public Form save(Form t) {
        sessionFactory.getCurrentSession().saveOrUpdate(t);
        return t;
    }

    @Override
    public Form delete(long id) {
        Form form = getByID(id);
        Query query = sessionFactory.getCurrentSession().createSQLQuery("delete from Form where id=:id");
        query.setParameter("id",id);
        query.executeUpdate();
        return form;
    }

    @Override
    public Form getByID(long id) {
        return sessionFactory.getCurrentSession().find(Form.class, id);
    }
}
