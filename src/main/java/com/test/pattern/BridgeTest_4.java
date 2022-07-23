package com.test.pattern;

public class BridgeTest_4 {
    public static void main(String[] args) {
        Shapee redCircle = new Circles(100,100,10,new RedCircle());
        redCircle.draw();

        Shapee greenCircle = new Circles(100,100,10,new GreenCircle());
        greenCircle.draw();


    }
}
interface DrawAPI{ // 桥梁：连接两种类别
    void drawCircle(int radius,int x,int y);
}
class RedCircle implements DrawAPI{
    @Override
    public void drawCircle(int radius, int x, int y){
        System.out.println("Drawing Circle[ color: red, radius: " + radius +", x: " +x+", "+ y +"]");
    }
}
class GreenCircle implements DrawAPI{
    @Override
    public void drawCircle(int radius,int x,int y){
        System.out.println("Drawing Circle[ color: green, radius: " + radius +", x: " +x+", "+ y +"]");
    }
}




abstract class Shapee{
    protected DrawAPI drawAPI;
    protected Shapee(DrawAPI drawAPI){
        this.drawAPI = drawAPI;
    }
    public abstract void draw();
}
class Circles extends Shapee{
    private int x,y,radius;
    public Circles(int x, int y,int radius, DrawAPI drawAPI){
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    @Override
    public void draw(){
        drawAPI.drawCircle(radius,x,y);
    }
}
