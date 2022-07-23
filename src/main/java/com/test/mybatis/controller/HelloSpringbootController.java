package com.test.mybatis.controller;

import com.test.mybatis.bean.MyBean;
import com.test.mybatis.service.IDemo1;
import com.test.mybatis.service.Jpademo;
import com.test.mybatis.service.MybatisDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RestController
@Controller
@ResponseBody
@Component
public class HelloSpringbootController {
    @Autowired
    private IDemo1 demo1;

    @Autowired
    private Jpademo jpademo;

    @Autowired
    private MybatisDemo mybatisDemo;

    @Autowired
    ResourceLoader resourceLoader;


    @GetMapping("/hello")
    public String hello(Model model) {
//        int a = 6/0;
        Map<String, Object> map = model.asMap();
        return map.toString();
    }


    @RequestMapping(value = "/jpa", method = RequestMethod.GET)
    public String world() {
        jpademo.test1();
        return "hello world";
    }
    @GetMapping("/mybean")
    public String mybean() {
        Map<String, Object> param = new HashMap<>();
        param.put("id","dd");
        List<MyBean> myBeanById = mybatisDemo.getMyBeanById(param);
        MyBean myBean = myBeanById.get(0);
        return myBean.getAge();
    }
    @GetMapping("/save")
    public String testSave(){
        MyBean user = new MyBean();
        user.setAge("11");
        user.setId("001");
        user.setName("haha");
        mybatisDemo.save(user);
//        throw new RuntimeException("异常抛出");
        return "success";
    }


}
