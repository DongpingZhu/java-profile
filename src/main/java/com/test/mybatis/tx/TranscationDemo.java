package com.test.mybatis.tx;

import org.springframework.transaction.annotation.Transactional;


public class TranscationDemo {

    @Transactional(transactionManager = "transactionManager")
    public void testTx(int a , int b){
        int sum =  a+b;


    }
}
