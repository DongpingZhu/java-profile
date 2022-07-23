package com.test.testdir;

import java.util.Map;

public class Demo extends AbstractDemo {
    private Map<String, String> map;
    public Demo(Map<String, String> param){
        this.map = param;
    }
    public Demo(){
        this(null);
    }
    public Map<String, String> getMap(){
        return map;
    }
}
