package com.test.mybatis.service.impl;

import com.test.mybatis.bean.MyBean;
import com.test.mybatis.dao.MybatisDemoMapper;
import com.test.mybatis.service.MybatisDemo;
import com.test.spring.framework.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MyBatisDemoImpl implements MybatisDemo {

    @Autowired
    MybatisDemoMapper mybatisDemoMapper;

    @Override
    public List<MyBean> getMyBeanById(Map<String, Object> param) {
        return mybatisDemoMapper.list(param);
    }
    @Override
    public void save(MyBean user){
        mybatisDemoMapper.insert(user);
    }
}
