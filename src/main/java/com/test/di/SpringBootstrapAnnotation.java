package com.test.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class SpringBootstrapAnnotation {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        for (String s : ctx.getBeanDefinitionNames()) {
            System.out.println(s);
        }

    }
}
