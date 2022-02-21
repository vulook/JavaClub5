package com.softserve.Dao;

import com.softserve.entity.UserRole;

public interface RoleDao {

	UserRole findRoleByName(String theRoleName);
	
}
