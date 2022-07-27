package com.test.spring.test;

import com.test.spring.framework.ZhuApplication;
import com.test.spring.test.service.UserService;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ApplicationContext;

public class SpringTest {
    public static void main(String[] args) {
        // 1.创建spring容器：
        // 2.bean的生命周期：实例化（反射），属性填充（Autowired），初始化（非必须，自定义，整合第三方库。前面两步已经构造了实例bean，即已经可以使用了）
        ZhuApplication application = new ZhuApplication(AppConfig.class);
        // 原型bean：
        System.out.println(application.getBean("userService"));
        System.out.println(application.getBean("userService"));
        System.out.println(application.getBean("userService"));
        // 单例bean：
        System.out.println(application.getBean("singletonBean"));
        System.out.println(application.getBean("singletonBean"));
        System.out.println(application.getBean("singletonBean"));
        // 依赖注入：
        UserService userService = (UserService) application.getBean("userService");
        userService.test();
        //



    }
}
