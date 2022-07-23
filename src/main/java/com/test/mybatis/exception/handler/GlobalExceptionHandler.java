package com.test.mybatis.exception.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // 监控Controller层出现的各种异常
public class GlobalExceptionHandler {

    @ExceptionHandler
    public void arithmeticException(ArithmeticException e, HttpServletResponse response) throws IOException {

        System.out.println("算数运算错误----------------");
        PrintWriter writer = response.getWriter();
        writer.write(e.getMessage());
        writer.flush();
        writer.close();

    }

    @ModelAttribute(value="kk")
    public Map<String, String> kkInfo(){
        Map<String, String> map = new HashMap<>();
        map.put("id","0001");
        map.put("address", "beijing");
        return map;
    }

}
