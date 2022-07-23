package com.test.pattern;

public class DecoratorTest_6 {
    public static void main(String[] args) {
        IShape circle = new Circ();
        System.out.println("normal border------------------");
        circle.draw();

        ShapeDec redCir = new Red(new Circ());
        System.out.println("circle  red border------------");
        redCir.draw();

        ShapeDec redRec = new Red(new Rect());
        System.out.println("rect  red border-------------------");
        redRec.draw();


    }
}
interface IShape{
    void draw();
}
class Rect implements IShape{
    @Override
    public void draw() {
        System.out.println("Rect");
    }
}
class Circ implements IShape{
    @Override
    public void draw() {
        System.out.println("Circle");
    }
}


class ShapeDec implements IShape{
    protected IShape decoratedShape;
    public ShapeDec(IShape decoratedShape){
        this.decoratedShape = decoratedShape;
    }
    @Override
    public void draw(){
        decoratedShape.draw();
    }
}
class Red extends ShapeDec{
    public Red(IShape dec){
        super(dec);
    }
    @Override
    public void draw(){
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }
    private void setRedBorder(IShape redBorder){
        System.out.println("Border Red");
    }

}

