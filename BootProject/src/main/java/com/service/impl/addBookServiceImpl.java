package com.service.impl;


import com.dao.insertBookDao;
import com.dao.impl.insertBookDaoimpl;
import com.domain.book;
import com.service.addBookService;

public class addBookServiceImpl implements addBookService {

	@Override
	public int insertBook(book bookBean) {
		insertBookDao dao=new insertBookDaoimpl();
		int res=dao.insertBook(bookBean);
		return res;
	}


	

}
