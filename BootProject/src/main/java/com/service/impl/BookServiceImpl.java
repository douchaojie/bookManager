package com.service.impl;


import java.util.List;

import com.dao.BookDao;
import com.dao.impl.BookDaoimpl;
import com.domain.BookList;
import com.domain.book;
import com.service.BookService;

public class BookServiceImpl implements BookService {

	@Override
	public int insertBook(book bookBean) { //添加书籍
		BookDao dao=new BookDaoimpl();
		int res=dao.insertBook(bookBean);
		return res;
	}

	@Override
	public List<BookList> findBookByPage(int pageNo) {// 按当前页查找当前页的书籍

		BookDao dao=new BookDaoimpl();
	
		return dao.selectBookByPage(pageNo);
	}

	@Override
	public int findBookCount() {    // 查找总条数
		BookDao dao=new BookDaoimpl();
		return dao.selectTotalCount();
	}

	@Override
	public int deleteBookById(int id) {
		BookDao dao=new BookDaoimpl();
		return dao.deleteBookById(id);
	}

	@Override
	public book selectBookById(int id) {
		BookDao dao=new BookDaoimpl();
		return dao.selectBookById(id);
		}

	@Override
	public int updateBook(book bookBean) {
		BookDao dao=new BookDaoimpl();
		int res=dao.updateBook(bookBean);
		return res;
	}


	

}
