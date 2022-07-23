package com.test.mybatis.config;


import com.test.mybatis.bean.Pet;
import com.test.mybatis.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:beans.xml")
@Configuration(proxyBeanMethods = false) // proxyBeanMethod默认是true
public class MyConfig {

    @Bean   // 不传参数时，以方法名作为组件id，即此注入的bean为user01
    public User user01() {
        return new User("zhangsan", 18);
    }

    @Bean("tom")
    public Pet tomcatPet() {
        return new Pet("tomcat");
    }

    @ConditionalOnMissingBean(name="tom")  // 不存在tom这个bean时才会执行下面代码，排斥
//    @ConditionalOnBean(name="tom")  // 已存在tom这个bean时才会执行下面代码，依赖
    @Bean("tom1")
    public Pet tomcatPet01(){
        return new Pet("tomcat01");
    }
}
