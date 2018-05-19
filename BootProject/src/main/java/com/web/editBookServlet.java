package com.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.book;
import com.service.BookService;
import com.service.impl.BookServiceImpl;


@WebServlet("/editBook")
public class editBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public editBookServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String strId = req.getParameter("id");
		int id = Integer.parseInt(strId);
		BookService service = new BookServiceImpl();
	    book res=service.selectBookById(id);
		req.setAttribute("book",res);
	    req.getRequestDispatcher("editBook.jsp").forward(req, resp);
	    
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
