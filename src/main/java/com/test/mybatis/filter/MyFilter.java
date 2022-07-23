package com.test.mybatis.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "myFilter")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("Filter 初始化......");

    }

    @Override
    public void destroy() {
        System.out.println("Filter 销毁......");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter 测试！, 过滤中......");
        chain.doFilter(request, response);

    }
}

