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
		// 获取文件
		Part part = request.getPart("t_photo");
		String pubdate = request.getParameter("t_date");
		String vcode = request.getParameter("vcode");// 前端
		// 先判断验证码
		String Vcode = (String) request.getSession().getAttribute("validateCode");//
		if (!Vcode.equalsIgnoreCase(vcode)) {
			request.getRequestDispatcher("editBook").forward(request, response);

			return; // 提前结束

		}
		// 解决浏览的兼容
		String fileName = part.getHeader("Content-Disposition").split(";")[2].split("=")[1].replace("\"", "");
		String newFileName = "";
		// !fileName.equals("")||fileName!=null-----不要这样写
		if (!fileName.equals("")) {// 没有默认图片了再上上传图片（有没有默认图片就看filename是否为空）

			fileName = fileName.lastIndexOf("\\") == -1 ? fileName : fileName.substring(fileName.lastIndexOf("\\") + 1);
			// 重新起名
			String ext = fileName.substring(fileName.lastIndexOf('.') + 1);
			newFileName = UUID.randomUUID().toString() + "." + ext;
			part.write(request.getServletContext().getRealPath("upload/" + newFileName));

		}

		// --------------------

		book bookBean = new book();
		MyBeanUtils.populate(bookBean, request.getParameterMap(), pubdate);
		if (!fileName.equals("")) {
			bookBean.setT_photo(newFileName); // 修改文件
		}
		System.out.println(bookBean);

		BookService service = new BookServiceImpl();
		int ret = service.updateBook(bookBean);
		response.setContentType("text/html;charset=utf-8");
		if (ret > 0) { //更新成功
			response.sendRedirect("bookList");// 书籍列表

		} else { // 更新失败

			// 把用户刚刚填的回调
			request.setAttribute("book", bookBean);
			request.getRequestDispatcher("editBook.jsp").forward(request, response);

		}

	}

}
