package com.test.pattern.factory_2;

// 这种不是标准写法，这种写法不能扩展某个已知族的新产品，xxx_re是标准写法
// 基于简单工厂改进的应用于多族产品的抽象工厂模式，违背了开闭原则，这种写法耦合性太大
public class AbsFactoryTest {
    public static void main(String[] args) {

        Factory factoryB = new FactoryB();
        ProductB b1 = factoryB.getProductB("b1");
        b1.intro();

    }
}


// A族
interface ProductA{ // A族
    void intro();
}
class ProductA1 implements ProductA{
    @Override
    public void intro() {
        System.out.println("产品A--1");
    }
}
class ProductA2 implements ProductA{
    @Override
    public void intro() {
        System.out.println("产品A--2");
    }
}


// B族
interface ProductB{ // B族
    void intro();
}
class ProductB1 implements ProductB{
    @Override
    public void intro() {
        System.out.println("产品B--1");
    }
}
class ProductB2 implements ProductB{
    @Override
    public void intro() {
        System.out.println("产品B--2");
    }
}


interface Factory{
    ProductA getProductA(String a);
    ProductB getProductB(String b);
}
// 创建产品对应的工厂
class FactoryA implements Factory{
    @Override
    public ProductA getProductA(String a) { // 按照简单工厂的方式，使用一个统一的方法
        if(a == null){
            return null;
        }
        if(a.equals("a1")){
            return new ProductA1();
        }else if(a.equals("a2")){
            return new ProductA2();
        }else {
            return null;
        }
    }

    @Override
    public ProductB getProductB(String b) {
        return null;
    }
}
class FactoryB implements Factory{
    @Override
    public ProductA getProductA(String a) {
        return null;
    }

    @Override
    public ProductB getProductB(String b) { // 按照简单工厂的方式，使用一个统一的方法
        if(b == null){
            return null;
        }
        if(b.equals("b1")){
            return new ProductB1();
        }else if(b.equals("b2")){
            return new ProductB2();
        }else {
            return null;
        }
    }
}





