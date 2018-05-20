package com.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 设置访问权限
 * 
 */
@WebFilter("/*")
public class loginFilter implements Filter {

   
    public loginFilter() {
    }

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)resp;
		String uri = request.getRequestURI();
		
		// 不要拦截登录功能和验证码。还有一些插件
		if(uri.endsWith("/loginServlet")||uri.endsWith("/login.jsp")||uri.contains("/bower_components/")||uri.endsWith("/vcode.png"))
		{
			chain.doFilter(req, resp);
		
			return;
		}
		//判断是否登录，登录以后才有权限访问别的
		if(request.getSession().getAttribute("loginMsg")==null||!request.getSession().getAttribute("loginMsg").equals("1")){
			
			response.sendRedirect("login.jsp");
			return ;
		}else {
			
			chain.doFilter(req, resp);
			
		}
		
		

	}

	public void destroy() {
	}
}
