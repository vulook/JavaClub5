package com.softserve.Dao.Implementation;

import com.softserve.Dao.UserDao;
import com.softserve.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByUserEmail(String userEmail) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery("select u from User u where u.email =:uEmail",User.class);
		theQuery.setParameter("uEmail", userEmail);

		User theUser;
		try {
			theUser = (User) theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}
		return theUser;
	}

	@Override
	public void save(User theUser) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theUser);
	}

}
