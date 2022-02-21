package com.softserve.Dao.Implementation;

import com.softserve.Dao.RoleDao;
import com.softserve.entity.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public UserRole findRoleByName(String theRoleName) {

		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery("SELECT r FROM UserRole r where r.role=:roleName",UserRole.class);
		theQuery.setParameter("roleName", theRoleName);
		
		UserRole theRole;
		
		try {
			theRole = (UserRole) theQuery.getSingleResult();
		} catch (Exception e) {
			theRole = null;
		}
		return theRole;
	}

}
