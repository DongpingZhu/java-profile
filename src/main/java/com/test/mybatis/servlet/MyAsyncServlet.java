package com.test.mybatis.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@WebServlet(name = "servlet3.0-AsyncServlet",urlPatterns = "/ser03",asyncSupported = true)
public class MyAsyncServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        long zhu = System.currentTimeMillis();
        AsyncContext asyncContext = req.startAsync();
        asyncContext.addListener(new AsyncListener() { // 添加监听器，即各种状态的callback
            @Override
            public void onComplete(AsyncEvent event) throws IOException {

            }

            @Override
            public void onTimeout(AsyncEvent event) throws IOException {

            }

            @Override
            public void onError(AsyncEvent event) throws IOException {

            }

            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {

            }
        });
        asyncContext.start(()->{
            long zi = System.currentTimeMillis();
            try {
                TimeUnit.SECONDS.sleep(3);
                asyncContext.getResponse().getWriter().write("ser03");
                asyncContext.complete();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程耗时：" + (System.currentTimeMillis()-zi));

        });
        System.out.println("主线程耗时：" + (System.currentTimeMillis()-zhu));

    }


}
