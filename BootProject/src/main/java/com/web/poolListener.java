package com.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.utils.connectUtils;

@WebListener    //�����˼�

public class poolListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("���ӳؼ����ر�");
		connectUtils.closePool();
		
		
	}

}
