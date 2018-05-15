package com.dao;


import java.util.List;

import com.domain.book;

public interface BookDao {

	int insertBook(book bookBean);

	List<book> selectBookByPage(int pageNo);

	int selectTotalCount();



	
	
}
