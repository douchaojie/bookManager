package com.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.BookList;
import com.domain.book;
import com.domain.bookType;
import com.service.BookService;
import com.service.BookTypeService;
import com.service.impl.BookServiceImpl;
import com.service.impl.BookTypeServiceImpl;
import com.utils.pageSize;

@WebServlet("/bookList")
public class bookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pageStr = req.getParameter("pageNo");
		int pageNo;
		if (pageStr == null) {

			pageNo = 1;

		} else {

			pageNo = Integer.parseInt(pageStr);
		}
		BookService service = new BookServiceImpl();
		List<BookList> bookList = service.findBookByPage(pageNo);
		
		int totalCount = service.findBookCount();
		// 根据总条数计算总页数
		if (totalCount % pageSize.pageSizeCount == 0) {

			req.setAttribute("totalPage", totalCount / pageSize.pageSizeCount);

		} else {

			req.setAttribute("totalPage", totalCount / pageSize.pageSizeCount + 1);
		}
		req.setAttribute("bookList", bookList);
		req.setAttribute("pageNo", pageNo);
		req.getRequestDispatcher("bookList.jsp").forward(req, resp);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
