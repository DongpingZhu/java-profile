package com.test.pattern;

public class FacadeTest_13 {
    public static void main(String[] args) {
        ShapeFacade shapeFacade = new ShapeFacade();
        shapeFacade.drawCircle();
        shapeFacade.drawRectangle();
        shapeFacade.drawSquare();

    }
}
interface Shape1{
    void draw();
}
class Rectangle1 implements Shape1{

    @Override
    public void draw() {
        System.out.println("Rectangle::draw()");
    }
}
class Square1 implements Shape1{

    @Override
    public void draw() {
        System.out.println("Square::draw()");
    }
}

class Circle1 implements Shape1{
    @Override
    public void draw(){
        System.out.println("Circle::draw()");
    }
}
class ShapeFacade{
    private Shape1 rectangle;
    private Shape1 square;
    private Shape1 circle;
    public ShapeFacade(){
        this.rectangle = new Rectangle1();
        this.square = new Square1();
        this.circle = new Circle1();
    }
    public void drawRectangle(){
        this.rectangle.draw();
    }
    public void drawSquare(){
        this.square.draw();
    }
    public void drawCircle(){
        this.circle.draw();
    }

}
