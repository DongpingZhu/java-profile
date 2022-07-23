package com.test.spring.zhu.service;

import com.test.spring.framework.*;

@Component("userService")
@Scope("prototype")
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
        System.out.println("初始化bean");

    }

    public void test() {
        System.out.println(orderService);
        System.out.println(beanName);

    }


}
