package com.service;

import com.domain.admin;

public interface adminService {

	boolean selectUser(admin person);

	int updateUser(admin user);

}
