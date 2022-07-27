package com.test.spring.test.service;

import com.test.spring.framework.*;

@Component("userService")
@Scope("prototype") //原型bean
public class UserService implements BeanNameAware, InitializingBean {

    @Autowired
    private OrderService orderService;

    private String beanName;


    @Override
    public void setBeanName(String name) {
        beanName = name;
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("初始化bean: afterPropertiesSet()");

    }

    public void test() {
        System.out.println(orderService);
        System.out.println(beanName);

    }


}
