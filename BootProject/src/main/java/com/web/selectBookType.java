package com.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.bookType;
import com.service.BookTypeService;
import com.service.impl.BookTypeServiceImpl;

/**
 * Servlet implementation class selectBookType
 */
@WebServlet("/selectType")
public class selectBookType extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		BookTypeService service=new BookTypeServiceImpl();
		List<bookType> typeList=service.selectType();
		
		resp.setContentType("text/html;charset=utf-8");// html
		resp.getWriter().write("<script>");  //ת��Ϊscript
		String js="[";
		for (int i = 0; i <typeList.size();i++) {

			js+="{id:"+typeList.get(i).getId()+",bookType:'"+typeList.get(i).getBookType()+"'}";
			if(i<typeList.size()-1)
			{
				js+=",";
				
			}
		}
		js+="]";
		resp.getWriter().write("window.parent.fillType("+js+");");//��j�Ӵ���ȥ
		
		resp.getWriter().write("</script>");
		
		
	
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
