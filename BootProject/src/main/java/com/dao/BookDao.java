package com.dao;


import java.util.List;

import com.domain.BookList;
import com.domain.book;

public interface BookDao {

	int insertBook(book bookBean);

	List<BookList> selectBookByPage(int pageNo, String name, int tid);

	int selectTotalCount(String name, int tid);

	int deleteBookById(int id);

	book selectBookById(int id);

	int updateBook(book bookBean);


	



	
	
}
