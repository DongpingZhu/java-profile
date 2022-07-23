package com.test.pattern.factory_2;

// 产品族数目是确定的，可以扩增每族的新产品
// 基于工厂方法改进的应用于多族产品的抽象工厂模式，保证了开闭原则
public class AbsFactoryTest_re {
    public static void main(String[] args) {
        AbsFactory product33Factory = new Product33Factory();
        AbsFactory product11Factory = new Product11Factory();
        ProductAA productA33 = product33Factory.getProductAA();
        productA33.intro();

    }
}


// A族
interface ProductAA{ // A族
    void intro();
}
class ProductA11 implements ProductAA{
    @Override
    public void intro() {
        System.out.println("产品A--1");
    }
}
class ProductA22 implements ProductAA{
    @Override
    public void intro() {
        System.out.println("产品A--2");
    }
}
class ProductA33 implements ProductAA{
    @Override
    public void intro() {
        System.out.println("产品A--3");
    }
}


// B族
interface ProductBB{ // B族
    void intro();
}
class ProductB11 implements ProductBB{
    @Override
    public void intro() {
        System.out.println("产品B--1");
    }
}
class ProductB22 implements ProductBB{
    @Override
    public void intro() {
        System.out.println("产品B--2");
    }
}
class ProductB33 implements ProductBB{
    @Override
    public void intro() {
        System.out.println("产品B--3");
    }
}


interface AbsFactory{// 此接口要求了产品族
    ProductAA getProductAA();
    ProductBB getProductBB();
}

// 创建工厂：每个产品族的某系列产品
class Product11Factory implements AbsFactory{
    @Override
    public ProductAA getProductAA() {
        return new ProductA11();
    }
    @Override
    public ProductBB getProductBB() {
        return new ProductB11();
    }
}
class Product22Factory implements AbsFactory{
    @Override
    public ProductAA getProductAA() {
        return new ProductA22();
    }
    @Override
    public ProductBB getProductBB() {
        return new ProductB22();
    }
}
class Product33Factory implements AbsFactory{
    @Override
    public ProductAA getProductAA() {
        return new ProductA33();
    }
    @Override
    public ProductBB getProductBB() {
        return new ProductB33();
    }
}














