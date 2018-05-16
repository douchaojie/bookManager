package com.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.domain.admin;
import com.service.adminService;
import com.service.impl.adminServiceImpl;

  @WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public loginServlet() {
        // TODO Auto-generated constructor stub
    }

public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// 防止乱码
	request.setCharacterEncoding("utf-8");
	
	/*String name = request.getParameter("name");
	String pwd = request.getParameter("pwd");*/
	String vcode = request.getParameter("vcode");
	HttpSession session = request.getSession();
	String Vcode = (String) session.getAttribute("validateCode");
	
	// 如果验证码错误下面的不会再执行了
	if(!vcode.equalsIgnoreCase(Vcode))
	{
		request.setAttribute("vcodeResult","验证码错误！");
		request.getRequestDispatcher("login.jsp").forward(request, response);
		
		return;
		
	}
	
	admin person = new admin();
	try {
		BeanUtils.populate(person,request.getParameterMap());
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		e.printStackTrace();
	}
	
	adminService select=new adminServiceImpl();	
   	boolean res=select.selectUser(person);
   	
   	
	if(res)
	{
		//request.setAttribute("name",person.getName());
		request.getSession().setAttribute("loginUser",person);
		response.sendRedirect("bookList");// 重定向到书籍列表
		
		
		
	}else{
		
		request.setAttribute("name",person.getName());
		request.getRequestDispatcher("login.jsp").forward(request, response);	
		
	}
   
		
	
	
	
	}

}
