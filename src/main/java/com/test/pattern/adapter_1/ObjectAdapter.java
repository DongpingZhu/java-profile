package com.test.pattern.adapter_1;



public class ObjectAdapter {
    public static void main(String[] args) {
        Adapter11 adapter11 = new Adapter11(new Adaptee1());
        adapter11.request();

    }
}

interface Target1{
    void request();
}
class Adaptee1{
    void sprcificRequest(){
        System.out.println("adaptee...");
    }
}

class Adapter11 implements Target1{
    private Adaptee1 adaptee1; // 组合

    public Adapter11(Adaptee1 adaptee1){
        this.adaptee1 = adaptee1;
    }
    @Override
    public void request() {
        adaptee1.sprcificRequest();
    }
}

