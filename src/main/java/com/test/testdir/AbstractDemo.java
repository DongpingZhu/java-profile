package com.test.testdir;


public abstract class AbstractDemo implements IDemo {

    @Override
    public IDemo enable(boolean enable){
        return this;
    }

}
