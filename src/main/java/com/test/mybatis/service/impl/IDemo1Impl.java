package com.test.mybatis.service.impl;

import com.test.mybatis.service.IDemo1;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class IDemo1Impl implements IDemo1 {
    @Override
    public String test1() {
        ApplicationHome ah = new ApplicationHome(getClass());
        File source = ah.getSource();
        String name = source.getAbsolutePath();
        System.out.println("ApplicationHome name:"+name);
        File path = new File("");// 当前项目的目录
        return "project name:" + path.getAbsolutePath();

    }
    @Override
    public String test2(){



        return null;
    }
}
