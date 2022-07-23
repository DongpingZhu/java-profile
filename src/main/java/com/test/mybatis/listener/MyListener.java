package com.test.mybatis.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("MyListener 初始化......");
        ServletContext servletContext = sce.getServletContext();
        System.out.println("vm:"+servletContext.getVirtualServerName());

    }
}
