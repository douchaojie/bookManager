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
import com.service.BookService;
import com.service.impl.BookServiceImpl;
import com.utils.MyBeanUtils;

@WebServlet("/updateBook")
@MultipartConfig
public class updateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// ��ȡ�ļ�
		Part part = request.getPart("t_photo");
		String pubdate = request.getParameter("t_date");
		String vcode = request.getParameter("vcode");// ǰ��
		// ���ж���֤��
		String Vcode = (String) request.getSession().getAttribute("validateCode");//
		if (!Vcode.equalsIgnoreCase(vcode)) {
			request.getRequestDispatcher("editBook").forward(request, response);

			return; // ��ǰ����

		}
		// �������ļ���
		String fileName = part.getHeader("Content-Disposition").split(";")[2].split("=")[1].replace("\"", "");
		String newFileName = "";
		// !fileName.equals("")||fileName!=null-----��Ҫ����д
		if (!fileName.equals("")) {// û��Ĭ��ͼƬ�������ϴ�ͼƬ����û��Ĭ��ͼƬ�Ϳ�filename�Ƿ�Ϊ�գ�

			fileName = fileName.lastIndexOf("\\") == -1 ? fileName : fileName.substring(fileName.lastIndexOf("\\") + 1);
			// ��������
			String ext = fileName.substring(fileName.lastIndexOf('.') + 1);
			newFileName = UUID.randomUUID().toString() + "." + ext;
			part.write(request.getServletContext().getRealPath("upload/" + newFileName));

		}

		// --------------------

		book bookBean = new book();
		MyBeanUtils.populate(bookBean, request.getParameterMap(), pubdate);
		if (!fileName.equals("")) {
			bookBean.setT_photo(newFileName); // �޸��ļ�
		}
		System.out.println(bookBean);

		BookService service = new BookServiceImpl();
		int ret = service.updateBook(bookBean);
		response.setContentType("text/html;charset=utf-8");
		if (ret > 0) { //���³ɹ�
			response.sendRedirect("bookList");// �鼮�б�

		} else { // ����ʧ��

			// ���û��ո���Ļص�
			request.setAttribute("book", bookBean);
			request.getRequestDispatcher("editBook.jsp").forward(request, response);

		}

	}

}
