package com.test.jackson;


import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;

public class Demo1 {
    public static void main(String[] args) throws Exception {
        Writer wanger = new Writer("wanger", 18);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(wanger);
        System.out.println(jsonString);
        String jsonString1 = "{\n" +
                "   \"name\" : \" zhangsan\", \n" +
                "   \"age\" : 18\n" +
                "}";
        Writer deserializedWriter = mapper.readValue(jsonString1, Writer.class);
        System.out.println(deserializedWriter);

    }
}

class Writer {
    private String name;
    private int age;

    public Writer() {

    }

    public Writer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Writer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
