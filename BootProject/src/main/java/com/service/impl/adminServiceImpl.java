package com.service.impl;

import com.dao.adminDao;
import com.dao.impl.adminDaoImpl;
import com.domain.admin;
import com.service.adminService;

public class adminServiceImpl implements adminService {

	@Override
	public boolean selectUser(admin person) {
		adminDao dao=new adminDaoImpl();
		
		return dao.loginUser(person);
	}

	@Override
	public int updateUser(admin user) { //ĞŞ¸ÄÃÜÂë
		adminDao dao=new adminDaoImpl();
		return dao.updateUser(user);
	}

}
