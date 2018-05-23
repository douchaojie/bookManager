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
/*
 * 
 * 乱码问题已解决
 */
@WebServlet("/bookList")
public class bookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String pageStr = req.getParameter("pageNo");
		String name = req.getParameter("name");
		if(name!=null&&!name.equals(""))
		{
		//解决get请求中文乱码问题
		 name = new String(name.getBytes("iso-8859-1"),"utf-8");//想用iso-8859-1编码再用utf-8解码
		}
		 String tidStr = req.getParameter("tid");
		
		int pageNo;
		if (pageStr == null) {

			pageNo = 1;

		} else {

			pageNo = Integer.parseInt(pageStr);
		}
		
		int tid;
		if (tidStr==null) {
			tid=-1;
			
		} else {
			tid = Integer.parseInt(tidStr);
		}
		
		
		
		BookService service = new BookServiceImpl();
		List<BookList> bookList = service.findBookByPage(pageNo,name,tid);
		BookTypeService typeService = new BookTypeServiceImpl();
		List<bookType> typeList = typeService.selectType();
		
		int totalCount = service.findBookCount(name,tid);
		// 根据总条数计算总页数
		if (totalCount % pageSize.pageSizeCount == 0) {

			req.setAttribute("totalPage", totalCount / pageSize.pageSizeCount);

		} else {

			req.setAttribute("totalPage", totalCount / pageSize.pageSizeCount + 1);
		}
		req.setAttribute("name",name);
		req.setAttribute("tid",tid);
		req.setAttribute("typeList", typeList);
		req.setAttribute("bookList", bookList);
		req.setAttribute("pageNo", pageNo);
		req.getRequestDispatcher("bookList.jsp").forward(req, resp);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//doGet(request, response);
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String pageStr = req.getParameter("pageNo");
		String name = req.getParameter("name");
		 //name = new String(name.getBytes("iso-8859-1"),"utf-8");
		String tidStr = req.getParameter("tid");
		
		int pageNo;
		if (pageStr == null) {

			pageNo = 1;

		} else {

			pageNo = Integer.parseInt(pageStr);
		}
		
		int tid;
		if (tidStr==null) {
			tid=-1;
			
		} else {
			tid = Integer.parseInt(tidStr);
		}
		
		
		
		BookService service = new BookServiceImpl();
		List<BookList> bookList = service.findBookByPage(pageNo,name,tid);
		BookTypeService typeService = new BookTypeServiceImpl();
		List<bookType> typeList = typeService.selectType();
		
		int totalCount = service.findBookCount(name,tid);
		// 根据总条数计算总页数
		if (totalCount % pageSize.pageSizeCount == 0) {

			req.setAttribute("totalPage", totalCount / pageSize.pageSizeCount);

		} else {

			req.setAttribute("totalPage", totalCount / pageSize.pageSizeCount + 1);
		}
		req.setAttribute("name",name);
		req.setAttribute("tid",tid);
		req.setAttribute("typeList", typeList);
		req.setAttribute("bookList", bookList);
		req.setAttribute("pageNo", pageNo);
		req.getRequestDispatcher("bookList.jsp").forward(req, resp);

	}

}
