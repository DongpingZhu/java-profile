package com.test.pattern.factory_2;

public class FactoryMethodTest {
    public static void main(String[] args) {
        NewFactory newFactory = new NewFactory();
        INoodles1 noodles = newFactory.createNoodles();
        noodles.desc();

    }
}
// 解决简单工厂的开闭原则问题
interface INoodles1{
    void desc();
}
class Lz1 implements INoodles1{
    @Override
    public void desc() {
        System.out.println("Lz noodles");
    }
}
class Pm1 implements INoodles1{
    @Override
    public void desc() {
        System.out.println("Pm noodles");
    }
}
class Gk1 implements INoodles1{
    @Override
    public void desc() {
        System.out.println("Gk noodles");
    }
}
class New1 implements INoodles1{
    @Override
    public void desc(){
        System.out.println("New noodles");
    }
}




interface IFactory{ //保持开闭原则，不能修改
    INoodles1 createNoodles();
}


class LzFactory implements IFactory{ // 每种产品一个工厂，新增产品时，添加一个对应的工厂就行
    public INoodles1 createNoodles(){
        return new Lz1();
    }
}
class PmFactory implements IFactory{
    public INoodles1 createNoodles(){
        return new Pm1();
    }
}
class GkFactory implements IFactory{
    public INoodles1 createNoodles(){
        return new Gk1();
    }
}
class NewFactory implements IFactory{
    public INoodles1 createNoodles(){
        return new New1();
    }
}




