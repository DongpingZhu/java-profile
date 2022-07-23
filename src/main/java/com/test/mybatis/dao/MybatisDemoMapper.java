package com.test.mybatis.dao;

import com.test.mybatis.bean.MyBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface MybatisDemoMapper {

    List<MyBean> list(Map<String, Object> param);

    int insert(MyBean user);

    MyBean selectById(String id);

    int deleteById(String id);

    MyBean update(MyBean user);

}
