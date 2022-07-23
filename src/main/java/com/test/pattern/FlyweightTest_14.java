package com.test.pattern;

import lombok.Data;

import java.util.HashMap;

public class FlyweightTest_14 {
    private static final String colors[] = { "Red", "Green", "Blue", "White", "Black" };
    private static String getRandomColor() {
        return colors[(int)(Math.random()*colors.length)];
    }
    private static int getRandomX() {
        return (int)(Math.random()*100 );
    }
    private static int getRandomY() {
        return (int)(Math.random()*100);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Circle2 circle = (Circle2) Shape2Factory.getCircle(getRandomColor());// 共享元数据：即共享含有同一颜色的对象
            circle.setX(getRandomX());
            circle.setY(getRandomY());
            circle.setRadius(100);
            circle.draw();

        }

    }
}

interface Shape2{
    void draw();
}

@Data

class Circle2 implements Shape2{
    private String color;
    private int x;
    private int y;
    private int radius;
    public Circle2(String color){
        this.color = color;
    }

    @Override
    public void draw() {
        System.out.println("Circle2{" +
                "color='" + color + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", radius=" + radius +
                '}');
    }
}

class Shape2Factory{
    private static final HashMap<String, Shape2> circleMap = new HashMap<>();

    public static Shape2 getCircle(String color){
        Circle2 circle2 = (Circle2)circleMap.get(color);

        if(circle2 == null){
            circle2 = new Circle2(color);
            circleMap.put(color, circle2);
            System.out.println("创建了"+color +"颜色的circle");
        }
        return circle2;
    }
}
