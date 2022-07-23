package com.test.pattern;

import java.util.HashMap;
import java.util.Map;

public class Prototype_9 {
    public static void main(String[] args) {
        ShapeCache.loadCache();
        Shape clone1 = ShapeCache.getShape("1");
        Shape clone11 = ShapeCache.getShape("1");
        Shape clone111 = (Shape) clone1.clone();
        System.out.println("shape:"+clone1.getType());
        System.out.println(clone1==clone11); // false
        System.out.println(clone1==clone111); // false
        Shape clone2 = ShapeCache.getShape("2");
        System.out.println("shape:"+clone2.getType());
        Shape clone3 = ShapeCache.getShape("3");
        System.out.println("shape:"+clone3.getType());
    }
}

class ShapeCache{
    private static Map<String, Shape> shapeMap = new HashMap<>();
    static Shape getShape(String id){
        Shape cachedShape = shapeMap.get(id);
//        return cachedShape; //单例bean
        return (Shape) cachedShape.clone(); // 原型bean
    }
    static void loadCache(){
        Circle circle = new Circle();
        circle.setId("1");
        shapeMap.put(circle.getId(),circle);
        Rectangle rectangle = new Rectangle();
        rectangle.setId("2");
        shapeMap.put(rectangle.getId(),rectangle);
        Square square = new Square();
        square.setId("3");
        shapeMap.put(square.getId(),square);
    }
}


abstract class Shape implements Cloneable{
    private String id;
    protected String type;

    abstract void draw();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object clone(){
        Object o = null;
        try {
            o = super.clone(); // 浅复制
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }
    public Shape clone1(){
        Shape o = null;
        try {
            o = (Shape) super.clone(); // 浅复制
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }
}

class Rectangle extends Shape{
    public Rectangle(){
        type = "Rectangle";
    }
    @Override
    public void draw(){
        System.out.println("Inside Rectangle::draw() method.");
    }
}
class Square extends Shape{
    public Square (){
        type = "Square";
    }
    @Override
    public void draw(){
        System.out.println("Inside Square ::draw() method.");
    }
}
class Circle extends Shape{
    public Circle (){
        type = "Circle ";
    }
    @Override
    public void draw(){
        System.out.println("Inside Circle ::draw() method.");
    }
}

