package com.test.spring.test.service;

import com.test.spring.framework.BeanPostProcessor;
import com.test.spring.framework.Component;

@Component
public class ZhuBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("初始化前的增强处理: postProcessBeforeInitialization方法");

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("初始化后的增强处理: postProcessAfterInitialization方法");
        return bean;
    }
}
