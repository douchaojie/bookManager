package com.service;


import java.util.List;

import com.domain.book;

public interface BookService {

	int insertBook(book bookBean);

	List<book> findBookByPage(int pageNo);

	int findBookCount();



}
