package com.dao;

import com.domain.admin;

public interface adminDao {

	boolean loginUser(admin person);

	int updateUser(admin user);

}
