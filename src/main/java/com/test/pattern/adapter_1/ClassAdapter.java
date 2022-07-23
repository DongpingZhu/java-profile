package com.test.pattern.adapter_1;

public class ClassAdapter {
    public static void main(String[] args) {

        Adapter1 adapter = new Adapter1();
        adapter.request();


    }
}

interface Target{
    void request();
}
class Adaptee{
    void requestAdaptee(){
        System.out.println( "adaptee...");
    }
}

class Adapter1 extends Adaptee implements Target{ // 继承
    @Override
    public void request() {
        this.requestAdaptee();
    }
}
