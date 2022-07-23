package com.test.di;

import com.test.Application;
import com.test.spring.framework.Autowired;
import com.test.spring.framework.Scope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import(Dog.class)
@ComponentScan(basePackages = "com.test.di") // 自动扫描到注解的类并实例化注入到spring容器
public class AppConfig {

    public AppConfig(){
        System.out.println("spring容器初始化完成");
    }
//    @Bean(value = "person")
    @Bean  // value=方法名
    public Person person(){
        return new Person("张三",10);
    }
}
