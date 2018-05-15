package com.service.impl;

import java.util.List;

import com.dao.BookTypeDao;
import com.dao.impl.BookTypeDaoimpl;
import com.domain.bookType;
import com.service.BookTypeService;

public class BookTypeServiceImpl implements BookTypeService {

	@Override
	public List<bookType> selectType() {
		BookTypeDao dao=new BookTypeDaoimpl();
		return dao.selectTypeList();
	}

}
