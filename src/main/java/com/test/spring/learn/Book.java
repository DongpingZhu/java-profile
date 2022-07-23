package com.test.spring.learn;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Calendar;

public class Book implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    Calendar calendar = Calendar.getInstance();

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }


    @Override
    public void setBeanName(String name) {

    }


    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ApplicationContext applicationContext = null;

    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
