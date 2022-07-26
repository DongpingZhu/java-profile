package com.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableScheduling
@EnableTransactionManagement
@SpringBootApplication// (SecurityAutoConfiguration.class)  排除security安全验证
@MapperScan("com.test.mybatis.dao")
@MapperScan("com.test.neo.mapper")
@ServletComponentScan
@EnableAspectJAutoProxy
public class Application {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext();


        // 获取IOC容器：
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
//        System.out.println("-----------打印容器中所有bean------------------");
//        Arrays.stream(run.getBeanDefinitionNames()).forEach(System.out::println);
        Object testComponent = applicationContext.getBean("testComponent");
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        TransactionManager transactionManager = applicationContext.getBean(TransactionManager.class);
        System.out.println("默认数据源："+dataSource);
        System.out.println("mybatis事务管理器："+ transactionManager); //JpaTransactionManager



    }

}

