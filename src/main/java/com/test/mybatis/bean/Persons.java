package com.test.mybatis.bean;


public class Persons {
    public static void main(String[] args) throws Exception {
        Person personList = new Person(){{ // 匿名类
            this.setName("zhu");
            this.setAge(18);
            System.out.println(this);
        }};
        System.out.println(personList);
        System.out.println("last.....");
    }
}
