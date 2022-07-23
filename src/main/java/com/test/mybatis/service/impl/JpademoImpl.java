package com.test.mybatis.service.impl;

import com.test.mybatis.bean.Userz;
import com.test.mybatis.repository.UserzDao;
import com.test.mybatis.service.Jpademo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class JpademoImpl implements Jpademo {

    @Resource
    UserzDao userzDao;

    @Override
    public void test1() {
        Userz userz = userzDao.save(new Userz(null, "sss", "123456", "beijing"));
        System.out.println("添加用户: " + userz);
        Userz u = userzDao.findByUsernameAndPassword("sss", "123456");
        System.out.println("根据用户名和密码查询用户: " + u);

    }
}
