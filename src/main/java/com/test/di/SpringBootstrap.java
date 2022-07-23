package com.test.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringBootstrap {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:ctx.xml");
        HelloWorldPOJO helloworldbean = (HelloWorldPOJO)ctx.getBean("helloworldbean");
        System.out.println("spring 容器测试：" + helloworldbean.getMessage());

    }
}
