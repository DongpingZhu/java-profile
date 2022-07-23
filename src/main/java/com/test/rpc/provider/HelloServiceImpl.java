package com.test.rpc.provider;

import com.test.rpc.api.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String msg) {
        System.out.println("收到客户端消息：" + msg);
        if(msg!=null){
            return "已收到"+msg;
        }else {
            return "已收到";

        }
    }
}
