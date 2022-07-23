package com.test.pattern;

// jdk提供有原生java.util.Iterator接口
// 构造一个可迭代的容器对象
public class IteratorTest_7 {
    public static void main(String[] args) {
        Repos repos = new Repos();
        Iterator iterator = repos.getIterator();
        while (iterator.hasNext()){
            System.out.println((String) iterator.next());
        }
    }
}
interface Iterator{
    boolean hasNext();
    Object next();
}

class Repos{
    public String[] names = {"aa","bb","cc","dd"};

    public Iterator getIterator(){
        return new Iters();
    }

    private class Iters implements Iterator{
        int index;
        @Override
        public boolean hasNext(){
            if(index < names.length){
                return true;
            }
            return false;
        }
        @Override
        public Object next(){
            if(this.hasNext()){
                return names[index++];
            }
            return null;
        }
    }
}
