package com.test.mybatis.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/ser02")
public class MyServletJSP extends HttpServlet {

    // servlet最初的网页构建方式，以字符串的形式构建：即为第一代web开发；
    // JSP技术：即为第二代web开发；
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("GBK");
        resp.getWriter().println("servlet 测试");
        Cookie name = new Cookie("name", "zhu");
        name.setMaxAge(60);
        Cookie age = new Cookie("age", "10");
        resp.addCookie(name);
        resp.addCookie(age);
        for (Cookie cookie : req.getCookies()) {
            resp.getWriter().println(cookie.getName() + ":" + cookie.getValue());
        }
        HttpSession session = req.getSession();
        session.setAttribute("username", "zhudongping");
        resp.getWriter().println("session:" + session.getAttribute("username"));


    }

}
