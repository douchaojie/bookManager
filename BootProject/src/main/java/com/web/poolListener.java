package com.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.utils.connectUtils;

@WebListener    //别忘了加

public class poolListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("连接池即将关闭");
		connectUtils.closePool();
		
		
	}

}
