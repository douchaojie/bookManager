package com.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.BookService;
import com.service.impl.BookServiceImpl;

@WebServlet("/deleteBook")
public class deleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public deleteBookServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String strId = req.getParameter("id");
		int id = Integer.parseInt(strId);
		BookService service = new BookServiceImpl();
		int res = service.deleteBookById(id);
		if(res>0)
		{
			resp.sendRedirect("bookList");
			
			
		}else {
			req.setAttribute("msg","É¾³ýÊ§°Ü");
			req.getRequestDispatcher("bookList").forward(req, resp);
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
