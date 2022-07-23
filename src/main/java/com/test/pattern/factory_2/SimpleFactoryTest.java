package com.test.pattern.factory_2;


public class SimpleFactoryTest {

    public static final int Lz = 1;
    public static final int Pm = 2;
    public static final int Gk = 3;

    public static void main(String[] args) {
        // 1. 简单工厂创建对象
        INoodles noodles_Lz = SimpleFactory.createNoodles(Lz);
        INoodles noodles_Pm = SimpleFactory.createNoodles(Pm);
        INoodles noodles_Gk = SimpleFactory.createNoodles(Gk);
        noodles_Gk.desc();
    }

}

// 当加新类别时，需要加if...else语句，违反了开闭原则
class SimpleFactory {
    public static INoodles createNoodles(int type){ // 提供一个统一的工厂方法来创建对象
        if(type==1){
            return new Lz();
        }else if(type==2){
            return new Pm();
        }else if(type==3){
            return new Gk();
        }else {
            return null;
        }
    }
}


// 只含有一族产品时
interface INoodles{
    void desc();
}
class Lz implements INoodles{
    @Override
    public void desc() {
        System.out.println("Lz noodles");
    }
}
class Pm implements INoodles{
    @Override
    public void desc() {
        System.out.println("Pm noodles");
    }
}
class Gk implements INoodles{
    @Override
    public void desc() {
        System.out.println("Gk noodles");
    }
}




