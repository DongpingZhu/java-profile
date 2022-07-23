package com.test.pattern;


// 模板模式：接口指定固定的步骤，由具体类去实现这些步骤接口
public class TemplateTest_12 {
    public static void main(String[] args) {
        Game game = new Cricket();
        game.play();
        game = new Football();
        game.play();

    }
}
interface Game{
    void initialize();
    void startPlay();
    void endPlay();
    default void play(){
        initialize();
        startPlay();
        endPlay();
    }
}
class Cricket implements Game{
    @Override
    public void initialize() {
        System.out.println("Cricket game finished");
    }

    @Override
    public void startPlay() {
        System.out.println("Cricket game initialized");

    }

    @Override
    public void endPlay() {
        System.out.println("Cricket game started");

    }
}

class Football implements Game{
    @Override
     public void initialize() {
        System.out.println("Football game finished");
    }

    @Override
    public void startPlay() {
        System.out.println("Football game initialized");

    }

    @Override
    public void endPlay() {
        System.out.println("Football game started");

    }
}
