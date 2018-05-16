package com.service;


import java.util.List;

import com.domain.BookList;
import com.domain.book;

public interface BookService {

	int insertBook(book bookBean);

	List<BookList> findBookByPage(int pageNo);

	int findBookCount();



}
