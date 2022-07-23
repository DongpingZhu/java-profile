package com.test.pattern;

import java.util.ArrayList;
import java.util.List;

public class ObserverTest_8 {
    public static void main(String[] args) {
        Subject subject = new Subject();
        new BinaryObserver(subject);
        new OctalObserver(subject);
        new HexObserver(subject);

        System.out.println("----------1-------------");
        subject.setState(1);
        System.out.println("--------------20----------");
        subject.setState(20);
    }
}

// Observer
abstract class Observer{
    protected Subject subject;
    public abstract void update();
}

// Observable
class Subject{
    private int state;
    private List<Observer> observers = new ArrayList<>();

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifuAllObservers();

    }
    public void attach(Observer observer){
        observers.add(observer);
    }
    public void notifuAllObservers(){
        for(Observer observer:observers){
            observer.update();
        }
    }
}
class BinaryObserver extends Observer{
    public BinaryObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }
    @Override
    public void update(){
        System.out.println("Binary String:" + Integer.toBinaryString(subject.getState()));
    }
}
class OctalObserver extends Observer{
    public OctalObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }
    @Override
    public void update(){
        System.out.println("Octal String:"+Integer.toOctalString(subject.getState()));
    }
}
class HexObserver extends Observer{
    public HexObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }
    @Override
    public void update(){
        System.out.println("Hex String:"+Integer.toOctalString(subject.getState()));
    }
}



























