package com.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.domain.admin;
import com.service.adminService;
import com.service.impl.adminServiceImpl;

@WebServlet("/updateUser")
public class updateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public updateUserServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String name = request.getParameter("name");
		String vcode = request.getParameter("vcode");
		HttpSession session = request.getSession();
		String Vcode = (String) session.getAttribute("validateCode");
		// �����֤���������Ĳ�����ִ����
		if (!vcode.equalsIgnoreCase(Vcode)) {
			request.setAttribute("vcodeResult", "��֤�����");
			request.getRequestDispatcher("updateUser.jsp").forward(request, response);

			return;

		}
		// �ж����������Ƿ�һ��
		if (!password.equals(password2)) {
			request.getRequestDispatcher("updateUser.jsp").forward(request, response);

			return;

		}
		admin user = new admin();
		user.setName(name);
		user.setPassword(password);
		adminService service = new adminServiceImpl();
		int res = service.updateUser(user);
		if (res>0) {  //�ɹ�
			
		response.sendRedirect("login.jsp");	
			
			
		}else {
			
		request.getRequestDispatcher("updateUser.jsp").forward(request, response);	
			
		}

	}

}
