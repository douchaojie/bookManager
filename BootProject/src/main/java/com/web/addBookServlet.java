package com.web;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.domain.book;
import com.service.addBookService;
import com.service.impl.addBookServiceImpl;
import com.utils.MyBeanUtils;

/**
 * Servlet implementation class addBookServlet
 */
@WebServlet("/addBook")
@MultipartConfig//文件下载
public class addBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		request.setCharacterEncoding("utf-8");
		// 获取文件
		Part part = request.getPart("t_photo");
	     // 解决浏览的兼容
		String fileName = part.getHeader("Content-Disposition").split(";")[2].split("=")[1].replace("\"", "");
		fileName = fileName.lastIndexOf("\\") == -1 ? fileName : fileName.substring(fileName.lastIndexOf("\\") + 1);
		// 重新起名
		String ext = fileName.substring(fileName.lastIndexOf('.') + 1);
		String newFileName = UUID.randomUUID().toString() + "." + ext;
		part.write(request.getServletContext().getRealPath("upload/" + newFileName));
		
		
		/*String name = request.getParameter("name");
	  String author = request.getParameter("author");
	  String descri = request.getParameter("descri");
	  String price = request.getParameter("price");
	  String tid = request.getParameter("tid");
	  String photo = request.getParameter("photo");*/
	  String pubdate = request.getParameter("t_date");
	  String vcode = request.getParameter("vcode");// 前端
      
	  // 先判断验证码
	  String Vcode = (String) request.getSession().getAttribute("validateCode");// 
	  if(!Vcode.equalsIgnoreCase(vcode))
	  {
		  request.getRequestDispatcher("addBook.jsp").forward(request, response);;
		  
		  return ; // 提前结束
		  
	  }
	  
	  book bookBean = new book();
	  MyBeanUtils.populate(bookBean, request.getParameterMap(), pubdate);
	
	// 2 业务转发
	  
	/*  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	  Date pubDate = null;
		try {
			pubDate = sdf.parse(pubdate);// 改变时间的样式
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
    bookBean.setT_photo(newFileName); // 修改文件
	  
	 addBookService service=new addBookServiceImpl(); 
	  int ret=service.insertBook(bookBean);
	  response.setContentType("text/html;charset=utf-8");
	  if(ret>0) {   // 插入成功
		response.getWriter().write("添加成功");
		  
		  
	  }else {     // 插入失败
		  
		  //request.setAttribute("msg","添加失败");
		  request.getRequestDispatcher("addBook.jsp").forward(request, response);
		  
	  }
	  
	  
	  
	  
	
	
	
	
	
	}

	

}
