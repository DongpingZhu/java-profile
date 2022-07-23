package com.test.mybatis;

import com.test.mybatis.bean.Userz;
import com.test.mybatis.repository.UserzDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class StarterTest {

    @Resource
    UserzDao userzDao;

    @Test
    void testJpa(){

        Userz save = userzDao.save(new Userz(22L, "sss", "123456", "beijing"));

        Userz sss = userzDao.findByUsernameAndPassword("sss", "123456");
        System.out.println(sss);


    }

}
