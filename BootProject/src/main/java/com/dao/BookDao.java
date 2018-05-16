package com.dao;


import java.util.List;

import com.domain.BookList;
import com.domain.book;

public interface BookDao {

	int insertBook(book bookBean);

	List<BookList> selectBookByPage(int pageNo);

	int selectTotalCount();



	
	
}
