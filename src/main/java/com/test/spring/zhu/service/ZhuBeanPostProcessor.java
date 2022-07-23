package com.test.spring.zhu.service;

import com.test.spring.framework.BeanPostProcessor;
import com.test.spring.framework.Component;

@Component
public class ZhuBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("初始化前......");

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("初始化后......");
        return bean;
    }
}
