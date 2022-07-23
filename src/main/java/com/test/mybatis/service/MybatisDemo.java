package com.test.mybatis.service;


import com.test.mybatis.bean.MyBean;

import java.util.List;
import java.util.Map;

public interface MybatisDemo {

    List<MyBean> getMyBeanById(Map<String, Object> param);

    void save(MyBean user);

}
