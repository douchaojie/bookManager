package com.service.impl;

import java.util.List;

import com.dao.selectBookTypeDao;
import com.dao.impl.selectBookTypeDaoimpl;
import com.domain.bookType;
import com.service.selectBookService;

public class selectBookServiceImpl implements selectBookService {

	@Override
	public List<bookType> selectType() {
		selectBookTypeDao dao=new selectBookTypeDaoimpl();
		return dao.selectTypeList();
	}

}
