package com.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.bookType;
import com.service.selectBookService;
import com.service.impl.selectBookServiceImpl;

/**
 * Servlet implementation class selectBookType
 */
@WebServlet("/selectType")
public class selectBookType extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		selectBookService service=new selectBookServiceImpl();
		List<bookType> typeList=service.selectType();
		req.setAttribute("typeList",typeList);
		req.getRequestDispatcher("addBook.jsp").forward(req, resp);
	
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
