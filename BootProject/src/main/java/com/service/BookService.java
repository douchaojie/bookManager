package com.service;


import java.util.List;

import com.domain.BookList;
import com.domain.book;

public interface BookService {

	int insertBook(book bookBean);

	List<BookList> findBookByPage(int pageNo, String name, int tid);

	int findBookCount(String name, int tid);


	int deleteBookById(int id);

	book selectBookById(int id);

	int updateBook(book bookBean);



}
