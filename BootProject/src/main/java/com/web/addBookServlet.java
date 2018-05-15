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
@MultipartConfig//�ļ�����
public class addBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		request.setCharacterEncoding("utf-8");
		// ��ȡ�ļ�
		Part part = request.getPart("t_photo");
	     // �������ļ���
		String fileName = part.getHeader("Content-Disposition").split(";")[2].split("=")[1].replace("\"", "");
		fileName = fileName.lastIndexOf("\\") == -1 ? fileName : fileName.substring(fileName.lastIndexOf("\\") + 1);
		// ��������
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
	  String vcode = request.getParameter("vcode");// ǰ��
      
	  // ���ж���֤��
	  String Vcode = (String) request.getSession().getAttribute("validateCode");// 
	  if(!Vcode.equalsIgnoreCase(vcode))
	  {
		  request.getRequestDispatcher("addBook.jsp").forward(request, response);;
		  
		  return ; // ��ǰ����
		  
	  }
	  
	  book bookBean = new book();
	  MyBeanUtils.populate(bookBean, request.getParameterMap(), pubdate);
	
	// 2 ҵ��ת��
	  
	/*  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	  Date pubDate = null;
		try {
			pubDate = sdf.parse(pubdate);// �ı�ʱ�����ʽ
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
    bookBean.setT_photo(newFileName); // �޸��ļ�
	  
	 addBookService service=new addBookServiceImpl(); 
	  int ret=service.insertBook(bookBean);
	  response.setContentType("text/html;charset=utf-8");
	  if(ret>0) {   // ����ɹ�
		response.getWriter().write("��ӳɹ�");
		  
		  
	  }else {     // ����ʧ��
		  
		  //request.setAttribute("msg","���ʧ��");
		  request.getRequestDispatcher("addBook.jsp").forward(request, response);
		  
	  }
	  
	  
	  
	  
	
	
	
	
	
	}

	

}
